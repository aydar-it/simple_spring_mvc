package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.MyFactory;
import com.geekbrains.spring.mvc.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ProductRepository {
    private SessionFactory sf;

    @PostConstruct
    public void postConstruct() {
        sf = MyFactory.getSessionFactory();
    }

    public Long save(Product product) {
        Session session = sf.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(product);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public Product find(Long id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public List<Product> getAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Product> result = session.createQuery("from Product", Product.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void delete(long id) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Product WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
