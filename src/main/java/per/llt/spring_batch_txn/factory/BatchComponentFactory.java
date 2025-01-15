package per.llt.spring_batch_txn.factory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import per.llt.spring_batch_txn.processor.TXNItemProcessor;
import per.llt.spring_batch_txn.model.TxnDto;
import per.llt.spring_batch_txn.repo.TXNRepository;

import java.util.List;

@Component
public class BatchComponentFactory {

    private final TXNRepository txnRepository;

    public BatchComponentFactory(TXNRepository txnRepository) {
        this.txnRepository = txnRepository;
    }

    /**
     * 🚀Factory method for creating processors 🚀
     **/
    public static <T> ItemProcessor<T, T> getProcessor(Class<T> entityClass) {
        if (entityClass == TxnDto.class) {
            return (ItemProcessor<T, T>) new TXNItemProcessor();
        } else {
            throw new IllegalArgumentException("No processor found for class: " + entityClass.getName());
        }
    }

    /**
     * 🚀Factory method for creating writers 🚀
     **/
    public <T> ItemWriter<T> getWriter(Class<T> entityClass) {
        if (entityClass == TxnDto.class) {
            return (ItemWriter<T>) transaction -> txnRepository.saveAll((List<TxnDto>) transaction);
        } else {
            throw new IllegalArgumentException("No writer found for class: " + entityClass.getName());
        }
    }
}
