package by.academy.it.mysql;

import by.academy.it.ClientDaoFactory;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ClientDaoImplTest {

    private ClientDaoImpl clientDao;

    @org.junit.Before
    public void setUp() throws Exception {
        try {
            clientDao = (ClientDaoImpl) ClientDaoFactory.getClientDao("mysql_test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void create() {
    }

    @org.junit.Test
    public void read() {
    }

    @org.junit.Test
    public void readAll() {
        assertFalse(clientDao == null);
    }

    @org.junit.Test
    public void update() {
    }

    @org.junit.Test
    public void delete() {
    }

    @org.junit.Test
    public void testDelete() {
    }

    @org.junit.Test
    public void getMaxId() {
    }
}