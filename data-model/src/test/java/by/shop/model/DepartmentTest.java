package by.shop.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentTest extends ModelTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createDepartmentWithoutEmployees() {
        //Given
        String departmentName = "Department1";
        Department department = createDepartment(departmentName);

        //When
        String departmentId = save(department);

        //Then
        assertNotNull(departmentId);
        Department savedDepartment = get(departmentId);
        assertEquals(departmentName, savedDepartment.getName());
        assertEquals(0, savedDepartment.getEmployees().size());

    }

    private Department get(String departmentId) {
        Transaction transaction = null;
        Department department = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            department = session.get(Department.class, departmentId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return department;
    }

    private String save(Department department) {
        String id = null;
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            id = (String) session.save(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return id;
    }

    private Department createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        return department;
    }

    @Test
    public void createDepartmentWithEmployees() {
        //Given
        String departmentName = "Department2";
        Department department = createDepartment(departmentName);
        int employeesCount = 2;
        List<Employee> employees = createEmployees(employeesCount, department);
        department.setEmployees(employees);

        //When
        String departmentId = save(department);

        //Then
        assertNotNull(departmentId);
        Department savedDepartment = get(departmentId);
        assertEquals(departmentName, savedDepartment.getName());
        assertEquals(employeesCount, savedDepartment.getEmployees().size());
    }

    private List<Employee> createEmployees(int employeesCount, Department department) {
        List<Employee> list = new ArrayList<>(employeesCount);
        for(int i = 0; i < employeesCount; i++) {
            Employee employee = new Employee();
            employee.setLastName("LastName" + i);
            employee.setFirstName("FirstName" + i);
            employee.setBirthDate(new Date());
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployee(employee);
            employee.setEmployeeDetails(employeeDetails);
            employee.setDepartment(department);
            list.add(employee);
        }
        return list;
    }

    @After
    public void tearDown() {
        super.tearDown();
    }
}