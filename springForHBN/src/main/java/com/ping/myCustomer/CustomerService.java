package com.ping.myCustomer;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    /* register
    進入dao層提交事務*/
    public void register(Customer customer) {
        System.out.println("----------------------------------");
        System.out.println("cusName :" + customer.getCusName());
        System.out.println("cusPhone :" + customer.getCusPhone());
        System.out.println("cusAddress :" + customer.getCusAddress());
        System.out.println("----------------------------------");
        customerDao.register(customer);
    }

    /* checkPhone
    提取此次表單設置的Phone 對資料庫內的所有phone做比對
    相同的話return true 回Controller返回ErrorPage
    */
    public boolean checkPhone(String s) {
        Session session = customerDao.getSessionFactory();
        SQLQuery sqlQuery = session.createSQLQuery("select * from customer where cusPhone = :value1 ;");
        sqlQuery.setParameter("value1", s);
        List list = sqlQuery.list();
        if (list.size() != 0) return true;
        session.close();
        return false;
    }

    /*  signIn()
    如果在資料庫中沒找到相同 cusName 與 cusPhone
    返回true 並返回ErrorPage.jsp
     */
    public boolean signIn(Customer customer) {
        Session session = customerDao.getSessionFactory();
        String sql = "select * from customer where cusName =? and cusPhone =?;";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        System.out.println(customer.getCusName());
        System.out.println(customer.getCusPhone());
        sqlQuery.setParameter(0,customer.getCusName());
        sqlQuery.setParameter(1,customer.getCusPhone());
        List list = sqlQuery.list();
        System.out.println(list.size());
        if (list.size() != 1) return true;
        session.close();
        return false;
    }
}
