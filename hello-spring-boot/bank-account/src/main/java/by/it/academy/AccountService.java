package by.it.academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account findAccountById(int id) {
        return accountRepository.read(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.readAll();
    }

    public Account saveNewAccount(Account account) {
        return accountRepository.create(account);
    }

    public Account updateAccount(int id, Account account) {
        Account saveAccount = accountRepository.read(id);
        if (saveAccount.equals(account)) {
            return saveAccount;
        }
        if (saveAccount.getBalance() != account.getBalance()
                ||saveAccount.getName() != account.getName()) {
            saveAccount.setBalance(account.getBalance());
            saveAccount.setName(account.getName());
            accountRepository.update(saveAccount);
        }
        return accountRepository.read(id);
    }

    public boolean deleteAccountById(int id) {
        return accountRepository.delete(id);
    }
}
