package by.academy.it.mysql;

import by.academy.it.ClientDaoFactory;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ClientDaoImplTest {

    private ClientDaoImpl clientDao;
    private IDatabaseConnection connection;

    @Before
    public void setUp() throws Exception {
        try {
            clientDao = (ClientDaoImpl) ClientDaoFactory.getClientDao("mysql_test");
            connection = new MySqlConnection(MySqlDataSource.getTestConnection(), "client_test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
        clientDao = null;
    }

    @Test
    public void create() {
    }

    @Test
    public void read() {
    }

    @Test
    public void readAll() {
        assertFalse(clientDao == null);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }


    @Test
    public void getMaxId() throws SQLException, DatabaseUnitException {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ClientDaoImplTest.class.getResourceAsStream("ClientDaoImplTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        //When
        int maxId = clientDao.getMaxId();

        //Then
        assertEquals(5, maxId);
        DatabaseOperation.DELETE.execute(connection, dataSet);
    }
}