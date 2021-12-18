package com.ping.myProduct;

<<<<<<< HEAD
import org.hibernate.*;
=======
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
<<<<<<< HEAD
        Session session = getSessionFactory();
        session.getTransaction();
        String sql = " from Product where proId > 0";
        Query query = session.createQuery(sql);
        List<Product> productList = query.list();
//        List<Product> list = new ArrayList(); // list存放各id的整行數據
//        List total;// total存放product表中有多少筆資料
//        Product product;
//        Session session = getSessionFactory();
//        String sql = "select * from product";
//        SQLQuery sqlQuery = session.createSQLQuery(sql);
//        total = sqlQuery.list();
//        for (int id = 1; id <= total.size(); id++) {
//            product = session.get(Product.class, id);
//            list.add(product);
//        }
        return productList;
=======
        List<Product> list = new ArrayList(); // list存放各id的整行數據
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
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
    }

    //以商品id從資料庫拿取商品的資料，再跳轉頁面時，能跟著傳輸商品資料
    @Override
    public Product findId(int proId) {
        Session session = getSessionFactory();
        Product product = session.get(Product.class, proId);
        session.close();
        return product;
    }

    //從資料庫取得商品數量
    @Override
    public boolean checkProducts(int proId, int buyNum) {
        //返回物件Product修改
        Session session = getSessionFactory();
        Product product = session.get(Product.class, proId);
        if (product.getProNum() >= buyNum) {
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public List<Product> showMyProducts(int cusId) {
        Session session = getSessionFactory();
        session.beginTransaction();
<<<<<<< HEAD
        Query query = session.createQuery("from Product where cusId = " + cusId);
        List<Product> list = query.list();
        session.close();
        return list;
    }

    //修改我的商品
    @Override
    public void alterMyProduct(Product product, int alterNum) {
        Session session = getSessionFactory();
        product.setProNum(alterNum);
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    //移除我的商品
    @Override
    public void dropMyProduct(Product product) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.createQuery("delete Product where ProId = " + product.getProId()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

=======
        Query Query = session.createQuery("from Product where cusId = " + cusId);
        List<Product> list = Query.list();
        System.out.println(list.toString());
        session.close();
        return list;
    }
    //        int result = product.getProNum() - buyNum;
//        product.setProNum(result);
//        對資料庫資料做真正修改
//        String sql = "update product set proNum = " + result + " where id = " + proId;
//        SQLQuery sqlQuery = session.createSQLQuery(sql);
//        session.beginTransaction();
//        session.save(product);
//        session.getTransaction().commit();
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
}
