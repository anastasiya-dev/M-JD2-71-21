package by.shop.model;

import by.shop.datasource.MySqlDataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductTest extends ModelTest {

    private static final Logger log = LoggerFactory.getLogger(ProductTest.class);


    @Before
    public void setUp() {
        super.setUp();
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
        String productId = null;
        try {
            tx = session.beginTransaction();

            //do some work
            productId = (String) session.save(product);

            product.serialNumber = "8888-8888";
            session.saveOrUpdate(product);

            tx.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (tx != null) tx.rollback();
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
            log.error(e.getMessage(), e);
            if (tx != null) tx.rollback();
        } finally {
            appSession.close();
        }

        //Then
        assertNotNull(product);
        assertEquals("Lenova Notebook", product.getName());
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

        Product product;
        Transaction tx = null;
        //When
        try (Session appSession = factory.openSession()) {
            tx = appSession.beginTransaction();
            product = appSession.get(Product.class, "5d463f59-ea1b-4f73-91e2-fdcbe3c90001");
            product.setProductNumber("99999_updated");
            product.setSerialNumber("11111_updated");
            appSession.flush();
            tx.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (tx != null) tx.rollback();
        }

        //Then
        DatabaseOperation.DELETE.execute(connection, dataSet);
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

        //When
        Product product1, product2;
        Transaction tx = null;
        try (Session appSession = factory.openSession()) {
            tx = appSession.beginTransaction();
            product1 = appSession.get(Product.class, "5d463f59-ea1b-4f73-91e2-fdcbe3c90000");
            appSession.delete(product1);

            product2 = appSession.get(Product.class, "5d463f59-ea1b-4f73-91e2-fdcbe3c90001");
            appSession.delete(product2);

            tx.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (tx != null) tx.rollback();
        }

        //Then
        DatabaseOperation.DELETE.execute(connection, dataSet);
        connection.close();

    }

    @Test
    public void query() throws SQLException, DatabaseUnitException {
        //Given:
        IDatabaseConnection connection = new MySqlConnection(
                MySqlDataSource.getTestConnection(),
                "shop_test");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ProductTest.class
                .getResourceAsStream("ProductTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        //When
        Session session = factory.openSession();
        Query<Product> query = session
                .createQuery("from Product p where p.name like :product", Product.class);
        query.setParameter("product", "%Notebook%");
        query.setFirstResult(0);
        query.setMaxResults(2);
        List<Product> products = query.list();

        //Then
        assertNotNull(products);
        assertTrue(products.size() == 2);
        DatabaseOperation.DELETE.execute(connection, dataSet);
        connection.close();
        if (session.isOpen()) session.close();
    }

    @Test
    public void searchByDate() throws SQLException, DatabaseUnitException, ParseException {
        //Given:
        IDatabaseConnection connection = new MySqlConnection(
                MySqlDataSource.getTestConnection(),
                "shop_test");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(ProductTest.class
                .getResourceAsStream("ProductTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

        //When
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Product.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = simpleDateFormat.parse("01-01-2020");
        Date endDate = simpleDateFormat.parse("01-04-2020");
        criteria.add(Restrictions.between("producedDate", startDate, endDate));
        List<Product> list = criteria.list();

        //Then
        assertNotNull(list);
        assertEquals(3, list.size());
        DatabaseOperation.DELETE.execute(connection, dataSet);
        connection.close();
    }


    @After
    public void tearDown() {
        super.tearDown();
    }
}