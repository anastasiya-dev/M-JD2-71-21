package by.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
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


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", producedDate=" + producedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(productNumber, product.productNumber))
            return false;
        if (!Objects.equals(serialNumber, product.serialNumber))
            return false;
        return Objects.equals(producedDate, product.producedDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (productNumber != null ? productNumber.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + (producedDate != null ? producedDate.hashCode() : 0);
        return result;
    }
}
