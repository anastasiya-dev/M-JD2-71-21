package by.shop.model;

import by.shop.datasource.MySqlDataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductTest {

    SessionFactory factory;

    @Before
    public void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.test.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    public void create() {
        //Given:
        Product product = new Product();
        product.name = "Lenova Notebook";
        product.productNumber = "1234abcd";
        product.serialNumber = "1234-6768";
        product.producedDate = new Date();

        //When:
        Session session = factory.openSession();
        Transaction tx = null;
        String productId;
        try {
            tx = session.beginTransaction();

            //do some work
            productId = (String) session.save(product);

            product.serialNumber = "8888-8888";
            session.saveOrUpdate(product);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        //Then:
        assertNotNull(productId);
        assertTrue(productId.length() > 1);
        assertNotNull(UUID.fromString(productId));
    }


    @Test
    public void read() throws DatabaseUnitException, SQLException {
        //Given:
        IDatabaseConnection connection = new MySqlConnection(
                MySqlDataSource.getTestConnection(),
                "shop_test");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ProductTest.class
                .getResourceAsStream("ProductTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        Session appSession = factory.openSession();

        //When
        Product product = null;
        Transaction tx = null;
        try {
            tx = appSession.beginTransaction();
            product = appSession.load(Product.class, "5d463f59-ea1b-4f73-91e2-fdcbe3c90000");
            System.out.println("Product Name: " + product.getName());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            appSession.close();
        }

        //Then
        assertNotNull(product);
        assertEquals("Lenova Notebook", product.name);
        DatabaseOperation.DELETE.execute(connection, dataSet);
        connection.close();

    }


    @Test
    public void update() throws DatabaseUnitException, SQLException {
        //Given:
        IDatabaseConnection connection = new MySqlConnection(
                MySqlDataSource.getTestConnection(),
                "shop_test");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ProductTest.class
                .getResourceAsStream("ProductTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        Session appSession = factory.openSession();

        //When
        Product product = null;
        Transaction tx = null;
        try {
            tx = appSession.beginTransaction();
            product = appSession.get(Product.class, "5d463f59-ea1b-4f73-91e2-fdcbe3c90001");

            product.setProductNumber("99999_updated");
            product.setSerialNumber("11111_updated");

            appSession.flush();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            appSession.close();
        }

        //Then
        connection.close();
    }

    @Test
    public void delete() throws DatabaseUnitException, SQLException {
        //Given:
        IDatabaseConnection connection = new MySqlConnection(
                MySqlDataSource.getTestConnection(),
                "shop_test");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ProductTest.class
                .getResourceAsStream("ProductTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        Session appSession = factory.openSession();

        //When
        Product product1 = null;
        Product product2 = null;
        Transaction tx = null;
        try {
            tx = appSession.beginTransaction();
            product1 = appSession.get(Product.class, "5d463f59-ea1b-4f73-91e2-fdcbe3c90000");
            appSession.delete(product1);

            product2 = appSession.get(Product.class, "5d463f59-ea1b-4f73-91e2-fdcbe3c90001");
            appSession.delete(product2);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            appSession.close();
        }

        //Then
        connection.close();

    }


    @After
    public void tearDown() {
        if (!factory.isClosed()) {
            factory.close();
            factory = null;
        }
    }
}