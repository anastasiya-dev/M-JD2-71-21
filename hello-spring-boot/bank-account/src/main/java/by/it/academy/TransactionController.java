package by.it.academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/accounts/{id}/transactions")
    public List<Transaction> transactions(@PathVariable int id) {
        return null;
    }

    @GetMapping("/accounts/{id}/transactions/{trId}")
    public Transaction transaction (
            @PathVariable int id,
            @PathVariable int trId
    ) {
        return null;
    }

    @PostMapping("/accounts/{id}/transactions")
    public Transaction createTransaction(
            @PathVariable int id,
            @RequestBody Transaction transaction
    ) {
        return null;
    }

    @PutMapping("/accounts/{id}/transactions/{trId}")
    public Transaction updateTransaction(
            @PathVariable int id,
            @PathVariable int trId,
            @RequestBody Transaction transaction
    ) {
        return null;
    }

    @DeleteMapping("/accounts/{id}/transactions/{trId}")
    public ResponseEntity<Transaction> deletedTransaction(
            @PathVariable int id,
            @PathVariable int trId
    ) {
        return null;
    }

}
