package com.ping.myCustomer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    /* signIn
    進入dao層提交事務*/
    public void signIn(Customer customer) {
        System.out.println("----------------------------------");
        System.out.println("cusName :" + customer.getCusName());
        System.out.println("cusPhone :" + customer.getCusPhone());
        System.out.println("cusAddress :" + customer.getCusAddress());
        System.out.println("----------------------------------");
        customerDao.SignIn(customer);
    }

    /* checkPhone
    提取此次表單設置的Phone 對資料庫內的所有phone做比對
    相同的話return true 回Controller返回ErrorPage
    */
    public boolean checkPhone(String s) {
        Session session = customerDao.getSessionFactory();
        Query query = session.createSQLQuery("select * from customer where cusPhone = :value1 ;");
        query.setParameter("value1", s);
        List list = query.list();
        if (list.size() != 0 ) return true;
        session.close();
        return false;
    }
}
