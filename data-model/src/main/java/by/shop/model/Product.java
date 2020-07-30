package by.shop.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    String id;

    @Column
    String name;

    @Column(name = "product_number")
    String productNumber;

    @Column(name ="serial_number" )
    String serialNumber;

    @Column(name = "produce_date")
    Date producedDate;
}
