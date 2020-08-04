package by.shop.model;

import java.util.List;

public class DepartmentUtil {

    public static void addEmployeesToDepartment(List<Employee> list, Department department) {
        for(Employee employee:list) {
            employee.setDepartment(department);
        }
        department.setEmployees(list);
    }
}
