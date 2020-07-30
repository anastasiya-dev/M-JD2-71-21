package by.shop.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String city;
    private String street;
    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToOne
    private Employee employee;
}
