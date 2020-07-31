package by.shop.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "product")
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
