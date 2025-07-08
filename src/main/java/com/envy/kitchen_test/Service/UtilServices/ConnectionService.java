package com.envy.kitchen_test.Service.UtilServices;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class ConnectionService {
    private static SessionFactory sessionFactory;
    public static void connect(){
        Properties props = new Properties();
        try {
            InputStream input = ConnectionService.class.getClassLoader().getResourceAsStream("hibernate.properties");
            props.load(input);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Configuration config = new Configuration();
        config.addProperties(props);
        System.out.println(props);
        sessionFactory = config.buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeConnection() {
            sessionFactory.close();
            sessionFactory = null;


    }
}
