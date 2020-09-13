package by.it.academy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findByAccountId(int accountId);

    Transaction findByAccountIdAndId(int accountId, int id);

    void deleteByAccountIdAndId(int accountId, int id);
}
