package by.it.academy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private int id;
    private Account account;
    private double amount;
    private String description;
}
