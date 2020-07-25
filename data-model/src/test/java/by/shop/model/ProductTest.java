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
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @After
    public void tearDown() {
        if (!factory.isClosed()) {
            factory.close();
            factory = null;
        }
    }
}