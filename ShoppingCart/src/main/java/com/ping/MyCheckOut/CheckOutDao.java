package com.ping.MyCheckOut;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CheckOutDao implements ICheckOut{
    @Autowired
    SessionFactory sessionFactory;
    private Session getSessionFactory(){
        return sessionFactory.openSession();
    }

    @Override
    public void save(CheckOut checkOut) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.save(checkOut);
        session.getTransaction().commit();
    }
}
