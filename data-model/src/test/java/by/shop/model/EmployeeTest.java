package by.shop.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeTest extends ModelTest {

    @Before
    public void setUp()  {
        super.setUp();
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
        super.tearDown();
    }
}