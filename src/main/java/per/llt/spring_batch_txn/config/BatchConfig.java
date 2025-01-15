package per.llt.spring_batch_txn.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.llt.spring_batch_txn.factory.BatchComponentFactory;
import per.llt.spring_batch_txn.factory.TextReaderFactory;
import per.llt.spring_batch_txn.model.TxnDto;

import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Value("${batch.file.name}")
    private String fileName;

    @Value("${batch.file.fields}")
    private String[] fileFields;

    @Value("${batch.chunk.size}")
    private int chunkSize;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final BatchComponentFactory batchComponentFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, BatchComponentFactory batchComponentFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.batchComponentFactory = batchComponentFactory;
    }

    @Bean
    public Job processJob() throws IOException {
        return jobBuilderFactory.get("processJob")
                .start(processStep(fileName, fileFields, TxnDto.class))
                .build();
    }


    public <T> Step processStep(String fileName, String[] fieldNames, Class<T> entityClass) {
        return stepBuilderFactory.get("processStep-" + entityClass.getSimpleName())
                .<T, T>chunk(chunkSize)
                .reader(itemReader(fileName, fieldNames, entityClass))
                .processor(batchComponentFactory.getProcessor(entityClass))
                .writer(batchComponentFactory.getWriter(entityClass))
                .build();
    }


    private <T> ItemReader<T> itemReader(String fileName, String[] fieldNames, Class<T> entityClass) {
        return TextReaderFactory.createReader(fileName, fieldNames, entityClass);
    }
}
