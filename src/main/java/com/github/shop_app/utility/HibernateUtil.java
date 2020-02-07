package com.github.shop_app.utility;

import com.github.shop_app.model.Product;
import com.github.shop_app.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = createSessionFactory();

    public HibernateUtil(){

    }
    private static SessionFactory createSessionFactory(){
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try{ properties.load(new FileInputStream("src/main/resources/Hibernate.properties")); }
        catch (Exception e){ e.printStackTrace(); }

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Product.class);

        configuration.setProperties(properties);
        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
