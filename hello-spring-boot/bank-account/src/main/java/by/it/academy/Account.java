package by.it.academy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

    private int id;
    private String name;
    private double balance;

}
