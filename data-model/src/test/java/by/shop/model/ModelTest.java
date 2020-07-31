package by.shop.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ModelTest {

    SessionFactory factory;
    StandardServiceRegistry registry;

    public void setUp()  {
        registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.test.cfg.xml")
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public void tearDown() {
        StandardServiceRegistryBuilder.destroy(registry);
        if (!factory.isClosed()) {
            factory.close();
            factory = null;
        }
    }
}
