package com.ping.myProduct;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao implements IProduct {
    @Autowired
    SessionFactory sessionFactory;

    public Session getSessionFactory() {
        return sessionFactory.openSession();
    }

    //對資料庫儲存經過驗證的產品表單內容
    @Override
    public void saveFile(Product product) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    /*
     取出資料庫id內所有資料
     session.get(Product.class, id) 資料庫內ID 整行數據
     添加至Product屬性內(get.set)
    */
    @Override
    public List<Product> showProducts() {
        List list = new ArrayList(); // list存放各id的整行數據
        List total;// total存放product表中有多少筆資料
        Product product;
        Session session = getSessionFactory();
        String sql = "select * from product";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        total = sqlQuery.list();
        for (int id = 1; id <= total.size(); id++) {
            product = session.get(Product.class, id);
            list.add(product);
        }
        return list;
    }
}
