package com.github.shop_app.utility;

import org.hibernate.Session;

public class UserSession {
    public static Session session;

    public static void createSession(){
        session = HibernateUtil.getSessionFactory().openSession();
    }
}
