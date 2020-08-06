package com.geekbrains.spring.mvc;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class MyFactory {
    private static SessionFactory sf;

    public static SessionFactory getSessionFactory() {
        if (sf == null) {
            sf = new Configuration()
                    .configure(new File("src\\main\\java\\com\\geekbrains\\spring\\mvc\\hibernate.cfg.xml"))
                    .buildSessionFactory();
        }
        return sf;
    }
}
