package by.it.academy.repository;

import by.it.academy.pojo.Recipient;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
public class UserRepository implements ApplicationContextAware, UserDao<Recipient> {

    private ApplicationContext context;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Recipient find(String userId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Recipient r where r.userId=:userId", Recipient.class)
                .setParameter("userId", userId)
                .list()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Override
    @Transactional
    public void create(Recipient recipient) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(recipient);
    }

    @Override
    public void update(Recipient recipient) {

    }

    @Override
    public Recipient read(Class clazz, Serializable id) {
        return null;
    }

    @Override
    public void delete(Recipient recipient) {

    }
}
