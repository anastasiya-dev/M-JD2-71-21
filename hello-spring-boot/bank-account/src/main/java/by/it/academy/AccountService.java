package by.it.academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account findAccountById(int id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account saveNewAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(int id, Account account) {
        Account saveAccount = accountRepository.findById(id).orElseThrow();
        if (saveAccount.equals(account)) {
            return saveAccount;
        }
        if (saveAccount.getBalance() != account.getBalance()
                || !saveAccount.getName().equals(account.getName())) {
            saveAccount.setBalance(account.getBalance());
            saveAccount.setName(account.getName());
            accountRepository.save(saveAccount);
        }
        return accountRepository.findById(id).orElseThrow();
    }

    public boolean deleteAccountById(int id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
