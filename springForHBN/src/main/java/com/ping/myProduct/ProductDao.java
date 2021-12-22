package com.ping.myProduct;

import com.ping.myBuyCustomer.BuyCustomerService;
import org.hibernate.*;
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
        session.flush();
        session.close();
    }

    /*
     取出資料庫id內所有資料
     session.get(Product.class, id) 資料庫內ID 整行數據
     添加至Product屬性內(get.set)
    */
    @Override
    public List<Product> showProducts() {
        Session session = getSessionFactory();
        session.getTransaction();
        session.flush();
        String sql = " from Product where proId > 0";
        Query query = session.createQuery(sql);
        List<Product> productList = query.list();
        session.close();
        return productList;
    }

    //以商品id從資料庫拿取商品的資料，再跳轉頁面時，能跟著傳輸商品資料
    @Override
    public Product findId(int proId) {
        Session session = getSessionFactory();
        session.flush();
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
    //客戶賣的所有商品
    @Override
    public List<Product> showMyProducts(int cusId) {
        Session session = getSessionFactory();
        session.flush();
        session.beginTransaction();
        Query query = session.createQuery("from Product where cusId = " + cusId);
        List<Product> list = query.list();
        session.getTransaction().commit();
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

    //依按鈕選擇，改變商品數量
    @Override
    public Product inAlterMyProduct(String button, String alterNum,String alterPrice, Product product) {
        Session session = getSessionFactory();
        session.beginTransaction();
        if (button.equals("修改")) {
            System.out.println("進入修改方法");
            product.setProNum(Integer.parseInt(alterNum));
            product.setProPrice(Integer.parseInt(alterPrice));
            session.update(product);
            session.getTransaction().commit();
            session.close();
            return product;
        } else if (button.equals("移除")) {
            System.out.println("進入移除方法");
            session.flush();
//            session.createQuery("delete  BuyCustomer where buyProId = " + product.getProId() +
//                    "and cusId = " + product.getCustomer().getCusId()).executeUpdate();
            session.createQuery("delete  BuyCustomer where buyProId = " + product.getProId()).executeUpdate();
            session.flush();
            session.createQuery("delete Product where ProId = " + product.getProId()).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("移除成功!");
        } else if(alterNum.equals("") || Integer.parseInt(alterNum) <= 0) {
            System.out.println("輸入錯誤 返回頁面");
        }
        return product;
    }
}
