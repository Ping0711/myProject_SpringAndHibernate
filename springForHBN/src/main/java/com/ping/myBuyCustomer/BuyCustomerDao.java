package com.ping.myBuyCustomer;

import com.ping.myProduct.Product;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuyCustomerDao implements IBuyCustomer {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return sessionFactory.openSession();
    }

    //把購買的資訊存入數據庫
    @Override
    public BuyCustomer saveBuyCustomer(BuyCustomer buyCustomer, Product product) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.saveOrUpdate(buyCustomer);
        session.flush();
        session.getTransaction().commit();
        buyCustomer = session.get(BuyCustomer.class, buyCustomer.getBuyId());
        session.close();
        return buyCustomer;
    }

    //以客戶id 把所有與客戶id相同中 購買的商品放入List
    @Override
    public List<BuyCustomer> showBuyProduct(int buyCusId) {
        Session session = getSessionFactory();
        session.beginTransaction();
        Query query = session.createQuery("from BuyCustomer where cusId = " + buyCusId);
        List<BuyCustomer> buyCustomerList = query.list();
        System.out.println(buyCustomerList.toString());
        return buyCustomerList;
    }

    //移除單個商品
    @Override
    public void dropMyProduct(Product product) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.createQuery("delete  BuyCustomer where buyProId = " + product.getProId() +
                "and cusId = " + product.getCustomer().getCusId()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    //修改單個商品
    @Override
    public void inAlterMyProduct(String choose, String proId,String alterNum) {
        Session session = getSessionFactory();
        session.beginTransaction();
        int buyProId = Integer.parseInt(proId);
        if (choose.equals("修改")) {
            System.out.println("進入修改方法");
            session.createQuery("update BuyCustomer set buyNum = "+alterNum+"where buyProId = " + buyProId).executeUpdate();
            session.getTransaction().commit();
        }
    }

    //清除購物車
    @Override
    public void dropMyProductAll(int cusId) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.createQuery("delete from BuyCustomer where cusId = "+cusId).executeUpdate();
        session.getTransaction().commit();
    }
}
