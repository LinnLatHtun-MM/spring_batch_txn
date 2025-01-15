package per.llt.spring_batch_txn.processor;

import org.springframework.batch.item.ItemProcessor;
import per.llt.spring_batch_txn.model.TransactionDescription;
import per.llt.spring_batch_txn.model.TxnDto;

public class TXNItemProcessor implements ItemProcessor<TxnDto, TxnDto> {

    @Override
    public TxnDto process(TxnDto txnDto) throws Exception {
        if (isValidDescription(txnDto.getDescription())) {
            return txnDto; // Return the valid transaction
        } else {
            throw new IllegalArgumentException("Invalid description: " + txnDto.getDescription());
        }
    }

    private boolean isValidDescription(String description) {
        for (TransactionDescription value : TransactionDescription.values()) {
            if (value.getDescription().equalsIgnoreCase(description)) {
                return true;
            }
        }
        return false;
    }
}
