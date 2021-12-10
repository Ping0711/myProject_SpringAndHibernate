package com.ping.myCustomer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *  對資料庫做實際操作動作
 *  #不會因為getSessionFactor報錯
 *  #Transactional
 *  用SignIn()只用getSessionFactory()報錯 解決:聲明Session用同一個get只用getSessionFactory();
 *  沒任何類上標註@Transactional 不會報錯
 */
@Repository
public class CustomerDao implements ICustomer{
    @Autowired
    SessionFactory sessionFactory ;

    public Session getSessionFactory(){
        return sessionFactory.openSession();
    }

    @Override
    public void register(Customer customer) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
        System.out.println("提交事務完成");
    }
}
