package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.MyFactory;
import com.geekbrains.spring.mvc.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UserRepository {
    private SessionFactory sf;

    @PostConstruct
    public void postConstruct() {
        sf = MyFactory.getSessionFactory();
    }

    public Long save(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(user);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public User find(Long id) {
        Session session = sf.openSession();
        session.beginTransaction();
        User user = session.find(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public List<User> getAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void delete(long id) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
