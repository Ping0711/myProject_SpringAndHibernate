package com.ping.myCustomer;

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
    public boolean check(String s) {
        Session session = customerDao.getSessionFactory();
        SQLQuery sqlQuery = session.createSQLQuery("select * from customer where cusPhone = :value1 ;");
        sqlQuery.setParameter("value1", s);
        List list = sqlQuery.list();
        if (list.size() != 0) return true;
        session.close();
        return false;
    }

    /*  signIn()
     */
    public Customer signIn(Customer customer) {
        Customer signInCus = customerDao.signIn(customer);
        System.out.println("-------------------------");
        System.out.println("將從資料庫找出用戶編碼");
        System.out.println("註冊會員請求登入:");
        System.out.println("ID :" + signInCus.getCusId());
        System.out.println("名稱 :" + signInCus.getCusName());
        System.out.println("手機 :" + signInCus.getCusPhone());
        System.out.println("-------------------------");
        return customer;
    }
    // 用會員id 尋找資料庫會員所有內容 返回所有欄位
    public Customer findById(int cusId){
        System.out.println("-------------------------");
        System.out.println("目前登入會員ID " + cusId);
        System.out.println("-------------------------");
        return customerDao.findByID(cusId);
    }
    // 以會員姓名與客戶電話 找到客戶ID 有找到後返回flag 進入signIn()方法
    public boolean checkCus(Customer customer){
        boolean flag = customerDao.checkCus(customer);
        System.out.println("-------------------------");
        System.out.println("找到客戶 顯示true ");
        System.out.println("未找到客戶 顯示false ");
        System.out.println("顯示 :"+flag);
        System.out.println("-------------------------");
        return flag;
    }
}
