package per.llt.spring_batch_txn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import per.llt.spring_batch_txn.model.TxnDto;
import per.llt.spring_batch_txn.repo.TXNRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TXNService {

    @Autowired
    private TXNRepository txnRepository;

    /**
     * 🌟 Get records with pagination and search by customerId, accountNumber, or description 🌟
     **/
    public Page<TxnDto> getTransaction(String customerId, String accountNumber, String description, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return txnRepository.findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(customerId, accountNumber, description, pageable);
    }

    /**
     * 🌟 Update description with concurrent handling (optimistic locking alternative) 🌟
     **/
    @Transactional
    public TxnDto updateDescription(Long id, String newDescription) throws Exception {
        Optional<TxnDto> txnOptional = txnRepository.findById(id);
        if (txnOptional.isPresent()) {
            TxnDto txn = txnOptional.get();
            txn.setDescription(newDescription);
            return txnRepository.save(txn);
        } else {
            throw new Exception("Record not found or version mismatch");
        }
    }


}
