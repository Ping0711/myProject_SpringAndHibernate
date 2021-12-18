package com.ping.myCustomer;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    //---將表單所有資料放入資料庫
    @Override
    public void register(Customer customer) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
        System.out.println("提交事務完成");
    }
    //---從輸入的id 尋找資料庫內相關客戶內容
    @Override
    public Customer findByID(int cusId) {
        Session session = getSessionFactory();
        Customer customer = session.get(Customer.class,cusId);
        session.close();
        return customer;
    }
    //---每次轉跳頁面時，保留客戶的資料，跟著傳輸資料到頁面顯示
    @Override
    public boolean checkCus(Customer customer) {
        Session session = getSessionFactory();
        String cusName = customer.getCusName();
        String cusPhone = customer.getCusPhone();
        String sql = "select * from customer where cusName =? and cusPhone =?;";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0, cusName);
        sqlQuery.setParameter(1, cusPhone);
        List list = sqlQuery.list();
        if (list.size() != 1) {
            System.out.println("資料庫查無資料");
            session.close();
            return false;
        }
        return true;
    }
    //---登入系統需要使用的方法 從名字與手機 找到客戶ID
    @Override
    public Customer signIn(Customer customer) {
        Session session = getSessionFactory();
        String cusName = customer.getCusName();
        String cusPhone = customer.getCusPhone();
        String sql = "select * from customer where cusName =? and cusPhone =?;";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0, cusName);
        sqlQuery.setParameter(1, cusPhone);
        List list = sqlQuery.list();
        //找到客戶的ID
        for(int i = 0 ; i <list.size()+1 ; i++){
            Customer customerId = session.get(Customer.class, i + 1);
            if(customerId.getCusName().equals(cusName) && customerId.getCusPhone().equals(cusPhone)){
                customer.setCusId(customerId.getCusId());
                System.out.println("找到用戶ID :" + customer.getCusId());
                break;
            }
        }
        return customer;
    }
}
