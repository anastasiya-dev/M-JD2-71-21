package by.shop.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

public class MeetingTest extends ModelTest {

    Session session;

    @Before
    public void setUp() {
        super.setUp();
        session = super.factory.openSession();
    }

    @Test
    public void create() {
        //Given
        Employee manager = createEmployee("E0");
        Employee employee1 = createEmployee("E1");
        Employee employee2 = createEmployee("E2");
        Employee employee3 = createEmployee("E3");

        Meeting meeting1 = createMeeting("M1");
        Meeting meeting2 = createMeeting("M2");
        Meeting meeting3 = createMeeting("M3");

        MeetingUtil.add(manager, Set.of(meeting1, meeting2, meeting3));
        MeetingUtil.add(employee1, Set.of(meeting1, meeting2));
        MeetingUtil.add(employee2, Set.of(meeting1, meeting3));
        MeetingUtil.add(employee3, Set.of(meeting2, meeting3));

        //When
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(manager);
            session.saveOrUpdate(employee1);
            session.saveOrUpdate(employee2);
            session.saveOrUpdate(employee3);
            session.saveOrUpdate(meeting1);
            session.saveOrUpdate(meeting2);
            session.saveOrUpdate(meeting3);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        //Then
        String managerID = manager.getId();
        Employee savedManager = session.get(Employee.class, managerID);
        assertEquals(3, savedManager.getMeetings().size());
    }

    private Meeting createMeeting(String m1) {
        Meeting meeting = new Meeting();
        meeting.setSubject(m1);
        Date startTime = new Date();
        meeting.setStartTime(startTime);
        meeting.setEndTime(new Date(startTime.getTime() + 1000 * 60 * 30));
        return meeting;
    }

    private Employee createEmployee(String e1) {
        Employee employee = new Employee();
        employee.setFirstName(e1);
        employee.setLastName(e1);
        employee.setBirthDate(new Date());
        return employee;
    }

    @After
    public void tearDown() {
        if (session.isOpen()) session.close();
        super.tearDown();
    }
}