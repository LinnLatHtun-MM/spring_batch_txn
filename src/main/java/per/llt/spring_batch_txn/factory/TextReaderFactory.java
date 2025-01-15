package per.llt.spring_batch_txn.factory;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class TextReaderFactory {

    /**
     * 🚀Factory Pattern to create a FlatFileItemReader for a specific entity class 🚀
     **/
    public static <T> FlatFileItemReader<T> createReader(String fileName, String[] fieldNames, Class<T> targetType) {
        return new FlatFileItemReaderBuilder<T>()
                .name(targetType.getSimpleName() + "Reader")
                .resource(new ClassPathResource(fileName))
                .delimited()
                .names(fieldNames)
                .targetType(targetType)
                .strict(false)
                .linesToSkip(1)
                .lineMapper(createLineMapper(targetType, fieldNames))
                .build();
    }

    /**
     * 🚀Factory method to create a LineMapper for a specific entity class 🚀
     **/
    private static <T> LineMapper<T> createLineMapper(Class<T> targetType, String[] fieldNames) {
        DefaultLineMapper<T> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(fieldNames);
        lineTokenizer.setDelimiter("|");

        BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(targetType);

        for (String fieldName : fieldNames) {
            Field field = ReflectionUtils.findField(targetType, fieldName);
            if (field != null) {
                ReflectionUtils.makeAccessible(field);
            }
        }

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
