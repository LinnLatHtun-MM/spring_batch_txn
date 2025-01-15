package per.llt.spring_batch_txn;

import org.mockito.Mock;
import per.llt.spring_batch_txn.processor.TXNItemProcessor;
import per.llt.spring_batch_txn.config.BatchConfig;
import per.llt.spring_batch_txn.factory.BatchComponentFactory;
import per.llt.spring_batch_txn.factory.TextReaderFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.item.ItemProcessor;
import per.llt.spring_batch_txn.model.TxnDto;
import per.llt.spring_batch_txn.repo.TXNRepository;

import static org.junit.jupiter.api.Assertions.*;

public class  BatchComponentFactoryTest {

    @Mock
    private BatchComponentFactory batchComponentFactory;

    @Mock
    private TextReaderFactory textReaderFactory;

    @Mock
    BatchConfig batchConfig;


    @Mock
    private TXNRepository txnRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        batchComponentFactory = new BatchComponentFactory(txnRepository);
    }

    @Test
    void testGetProcessor() {
        ItemProcessor<?, ?> processor = BatchComponentFactory.getProcessor(TxnDto.class);
        assertNotNull(processor);
        assertTrue(processor instanceof TXNItemProcessor);
    }

    @Test
    void testGetProcessorInvalidClass() {
        assertThrows(IllegalArgumentException.class, () -> BatchComponentFactory.getProcessor(String.class));
    }

    @Test
    void testGetWriterInvalidClass() {
        assertThrows(IllegalArgumentException.class, () -> batchComponentFactory.getWriter(String.class));
    }

}
