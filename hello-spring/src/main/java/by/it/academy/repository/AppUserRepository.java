package by.it.academy.repository;

import by.it.academy.pojo.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("appUserRepository")
public class AppUserRepository implements GenericDao<AppUser> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(AppUser appUser) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(appUser);
    }

    @Override
    public void update(AppUser appUser) {
        create(appUser);
    }

    @Override
    public AppUser read(Class clazz, Serializable id) {
        return sessionFactory
                .getCurrentSession()
                .get(AppUser.class, id);
    }

    @Override
    public void delete(AppUser appUser) {

    }

    @Override
    public AppUser find(String userId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from AppUser u where u.username=:username", AppUser.class)
                .setParameter("username", userId)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AppUser> findAll(String searchStr) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from AppUser", AppUser.class)
                .list();
    }
}
