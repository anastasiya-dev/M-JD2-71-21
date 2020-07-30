package by.shop.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EmployeeTest {

    SessionFactory factory;
    StandardServiceRegistry registry;

    @Before
    public void setUp()  {
        registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.test.cfg.xml")
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    @Test
    public void create() {
        //Given
        Employee employee = new Employee();
        employee.setBirthDate(new Date());
        employee.setFirstName("First Employee");
        employee.setLastName("Last Name Employee");

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setCity("Minsk");
        employeeDetails.setCountry(Country.UA);
        employeeDetails.setMobileNumber("375296101011");
        employeeDetails.setStreet("Platonova 39-1");
        employeeDetails.setEmployee(employee);

        employee.setEmployeeDetails(employeeDetails);

        //When
        String employeeId = save(employee);
        String employeeDetailsId = employee.getEmployeeDetails().getId();

        Employee savedEmployee = get(employeeId);

        //Then
        assertNotNull(employeeId);
        assertNotNull(employeeDetailsId);

        assertNotNull(savedEmployee);
        assertNotNull(savedEmployee.getEmployeeDetails());
        assertEquals(employeeDetailsId, savedEmployee.getEmployeeDetails().getId());
    }

    private String save(Employee employee) {
        Session session = factory.openSession();
        Transaction tx = null;
        String employeeId;
        try {
            tx = session.beginTransaction();
            employeeId = (String) session.save(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return employeeId;
    }

    public Employee get(String employeeId) {
        Session appSession = factory.openSession();
        Employee employee;
        Transaction tx = null;
        try {
            tx = appSession.beginTransaction();
            employee = appSession.get(Employee.class, employeeId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            appSession.close();
        }
        return employee;
    }

    @After
    public void tearDown() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}