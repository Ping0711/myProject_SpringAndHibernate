package com.ping.MySellProduct;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
public class SellProductDao implements ISellProduct {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return sessionFactory.openSession();
    }

    @Override
    public void save(SellProduct sellProduct, MultipartFile multipartFile) throws IOException {
        Session session = getSessionFactory();
        File dir = new File("C:\\Users\\MSI\\Desktop\\ShoppingCart\\target\\ShoppingCart\\WEB-INF\\sellPictures");
        if (!dir.exists()) {
            System.out.println("準備建立資料夾");
            boolean mkdir = dir.mkdir();
        }
        String fileName = multipartFile.getOriginalFilename(); //xx.jpg
        String randomUUID = UUID.randomUUID().toString().replace("-", ""); ///abcd
        String ext = FilenameUtils.getExtension(fileName); //jpg
        String filePath = "sellPictures/" + randomUUID + "." + ext; // sellPictures/abcd.jpg
        fileName = randomUUID + "." + ext;
        System.out.println("存入sellPictures 的圖片路徑 : " + filePath);
        multipartFile.transferTo(new File("C:\\Users\\MSI\\Desktop\\ShoppingCart\\web\\WEB-INF\\sellPictures\\" + fileName));
        multipartFile.transferTo(new File("C:\\Users\\MSI\\Desktop\\ShoppingCart\\target\\ShoppingCart\\WEB-INF\\sellPictures\\" + fileName));
        sellProduct.setSellPicture(filePath);
        //------------------------------------
        session.beginTransaction();
        session.saveOrUpdate(sellProduct);
        session.getTransaction().commit();
    }

    @Override
    public List<SellProduct> mySellProducts(int uid) {
        Session session = getSessionFactory();
        session.beginTransaction();
        Query query = session.createQuery("from SellProduct where uid = :value");
        query.setParameter("value", uid);
        List<SellProduct> sellProducts = query.list();
        return sellProducts;
    }

    @Override
    public boolean checkValue(SellProduct sellProduct) {
        Session session = getSessionFactory();
        session.beginTransaction();
        Query query = session.createQuery("from SellProduct where uid = ? and sellName = ?");
        query.setParameter(0, sellProduct.getUser().getUid());
        query.setParameter(1, sellProduct.getSellName());
        boolean flag = query.list().size() != 0;
        return flag;
    }

    @Override
    public SellProduct findMySellProduct(int sid) {
        Session session = getSessionFactory();
        session.beginTransaction();
        SellProduct sellProduct = session.get(SellProduct.class, sid);
        session.close();
        return sellProduct;
    }

    @Override
    public void alterMySellProduct(SellProduct sellProduct) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.saveOrUpdate(sellProduct);
        session.getTransaction().commit();
    }

    @Override
    public void dropMySellProduct(SellProduct mySellProduct) {
        File fileOfWeb = new File("C:\\Users\\MSI\\Desktop\\ShoppingCart\\target\\ShoppingCart\\WEB-INF\\" + mySellProduct.getSellPicture());
        File fileOfTarget = new File("C:\\Users\\MSI\\Desktop\\ShoppingCart\\web\\WEB-INF\\" + mySellProduct.getSellPicture());
        fileOfWeb.delete();
        fileOfTarget.delete();
        Session session = getSessionFactory();
        session.beginTransaction();
        session.delete(mySellProduct);
        session.getTransaction().commit();
    }

    @Override
    public List<SellProduct> allSellProducts() {
        Session session = getSessionFactory();
        session.beginTransaction();
        Query query = session.createQuery("from SellProduct");
        List<SellProduct> sellProductList = query.list();
        return sellProductList;
    }
}
