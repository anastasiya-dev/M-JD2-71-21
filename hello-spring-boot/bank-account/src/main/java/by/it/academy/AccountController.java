package by.it.academy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> accounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account account(@PathVariable int id) {
        return accountService.findAccountById(id);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveNewAccount(account);
    }

    @PutMapping("/accounts/{id}")
    public Account updatedAccount(@PathVariable int id,
                           @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable int id) {
        boolean result = accountService.deleteAccountById(id);
        return result ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
