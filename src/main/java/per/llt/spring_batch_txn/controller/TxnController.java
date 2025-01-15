package per.llt.spring_batch_txn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import per.llt.spring_batch_txn.model.TxnDto;
import per.llt.spring_batch_txn.service.TXNService;

@RestController("/transactions")
public class TxnController {

    @Autowired
    private TXNService txnService;

    @GetMapping("/")
    public Page<TxnDto> getRecords(
            @RequestParam(value = "customerId", defaultValue = "") String customerId,
            @RequestParam(value = "accountNumber", defaultValue = "") String accountNumber,
            @RequestParam(value = "description", defaultValue = "") String description,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return txnService.getTransaction(customerId, accountNumber, description, page, size);
    }

    @PatchMapping("/description/{id}")
    public ResponseEntity<TxnDto> updateDescription(
            @PathVariable Long id,
            @RequestParam String newDescription) throws Exception {
        TxnDto updatedTxn = txnService.updateDescription(id, newDescription);
        return ResponseEntity.ok(updatedTxn);
    }
}
