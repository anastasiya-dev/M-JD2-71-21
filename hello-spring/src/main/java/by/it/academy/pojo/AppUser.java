package by.it.academy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    private String userName;

    private String userPassword;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<AppRole> roles;

}
