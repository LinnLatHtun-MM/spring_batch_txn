package per.llt.spring_batch_txn;

import org.junit.Test;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ClassPathResource;
import per.llt.spring_batch_txn.factory.TextReaderFactory;
import per.llt.spring_batch_txn.model.TxnDto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TextReaderFactoryTest {

    @Test
    public void testCreateReader() {

        String fileName = "test.txt";
        String[] fieldNames = {"ACCOUNT_NUMBER","TRX_AMOUNT","DESCRIPTION","TRX_DATE","TRX_TIME","CUSTOMER_ID"};

        Class<TxnDto> targetType = TxnDto.class;

        FlatFileItemReader<TxnDto> reader = TextReaderFactory.createReader(fileName, fieldNames, targetType);

        // Verify the reader is not null
        assertNotNull("Reader should not be null", reader);

        // Verify the resource indirectly by checking the file's existence
        assertTrue("Resource file should exist",
                new ClassPathResource(fileName).exists());
    }
}

