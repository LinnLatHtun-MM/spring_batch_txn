package per.llt.spring_batch_txn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import per.llt.spring_batch_txn.model.TxnDto;
import per.llt.spring_batch_txn.repo.TXNRepository;
import per.llt.spring_batch_txn.service.TXNService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringBatchTxnDtoApplicationTests {

    @Mock
    private TXNRepository txnRepository;

    @InjectMocks
    private TXNService txnService;

    private TxnDto txn1;
    private TxnDto txn2;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        txn1 = new TxnDto();
        txn1.setId(1L);
        txn1.setAccountNumber("12345");
        txn1.setDescription("Description 1");
        txn1.setCustomerId("CUST001");

        txn2 = new TxnDto();
        txn2.setId(2L);
        txn2.setAccountNumber("67890");
        txn2.setDescription("Description 2");
        txn2.setCustomerId("CUST002");
    }

    /**
     * 🌟 Test: Get records with pagination and search by customerId, accountNumber, or description 🌟
     */
    @Test
    void testGetRecords() {
        Page<TxnDto> mockPage = new PageImpl<>(Arrays.asList(txn1, txn2));
        Pageable pageable = PageRequest.of(2, 10);

        when(txnRepository.findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
                anyString(), anyString(), anyString(), eq(pageable)))
                .thenReturn(mockPage);

        Page<TxnDto> result = txnService.getTransaction("CUST001", "12345", "Description", 2, 10);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("12345", result.getContent().get(0).getAccountNumber());

        verify(txnRepository, times(1))
                .findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
                        "CUST001", "12345", "Description", pageable);
    }

    /**
     * 🌟 Test: Update description successfully 🌟
     */
    @Test
    void testUpdateDescriptionSuccess() throws Exception {
        when(txnRepository.findById(1L)).thenReturn(Optional.of(txn1));
        when(txnRepository.save(any(TxnDto.class))).thenReturn(txn1);

        TxnDto updatedTxn = txnService.updateDescription(1L, "Updated Description");

        assertNotNull(updatedTxn);
        assertEquals("Updated Description", updatedTxn.getDescription());

        verify(txnRepository, times(1)).findById(1L);
        verify(txnRepository, times(1)).save(txn1);
    }

    /**
     * 🌟 Test: Update description when record not found 🌟
     */
    @Test
    void testUpdateDescriptionRecordNotFound() {
        when(txnRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> txnService.updateDescription(1L, "Updated Description"));

        assertEquals("Record not found or version mismatch", exception.getMessage());

        verify(txnRepository, times(1)).findById(1L);
        verify(txnRepository, times(0)).save(any(TxnDto.class));
    }
}
