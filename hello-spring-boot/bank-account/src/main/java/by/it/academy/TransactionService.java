package by.it.academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository repository;

    public List<Transaction> getAllTransaction(int accountId) {
        return repository.findByAccountId(accountId);
    }

    public Transaction findTransaction(int accountId, int id) {
        return repository.findByAccountIdAndId(accountId, id);
    }

    public Transaction createNewTransaction(int accountId, Transaction transaction) {
        transaction.setAccountId(accountId);
        return repository.save(transaction);
    }

    public Transaction updateTransaction(int accountId, int id, Transaction transaction) {
        transaction.setAccountId(accountId);
        transaction.setId(id);
        return repository.save(transaction);
    }

    @Transactional
    public boolean deleteTransaction(int accountId, int id) {
        if (repository.findByAccountIdAndId(accountId, id) != null) {
            repository.deleteByAccountIdAndId(accountId, id);
            return true;
        }
        return false;
    }
}
