package by.shop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Counter {

    @Id
    private int id;

    private long count;
}
