package per.llt.spring_batch_txn.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import per.llt.spring_batch_txn.model.TxnDto;

import java.util.Optional;

@Repository
public interface TXNRepository extends JpaRepository<TxnDto, Long> {

    Page<TxnDto> findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
            String customerId, String accountNumber, String description, Pageable pageable);

    Optional<TxnDto> findById(Long id);

}
