package by.it.academy;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class AccountRepository {

    private static List<Account> accountList;

    static {
        accountList = Collections.synchronizedList(new ArrayList<>());
        accountList.add(new Account(1, "My BYN account", 0));
    }

    public Account read(int id) {
        return accountList.stream()
                .filter(account -> account.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Account> readAll() {
        return List.copyOf(accountList);
    }

    public Account create(Account account) {
        accountList.add(account);
        return read(account.getId());
    }

    public void update(Account saveAccount) {
        //TODO
    }

    public boolean delete(int id) {
        return accountList.removeIf(
                account -> account.getId() == id
        );
    }
}
