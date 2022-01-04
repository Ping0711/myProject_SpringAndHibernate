package com.ping.MyCart;

import com.ping.MySellProduct.SellProduct;
import com.ping.MyUser.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class CartDao implements ICart {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return sessionFactory.openSession();
    }

    @Override
    public void save(Cart cart) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.saveOrUpdate(cart);
        session.getTransaction().commit();
    }

    @Override
    public void dropBuyProduct(SellProduct mySellProduct) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.createQuery("delete from Cart where sid = " + mySellProduct.getSid()).executeUpdate();
        session.close();
    }


    @Override
    public void dropAllMyCart(int uid) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.createQuery("delete from Cart where uid = " + uid).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<Cart> getMyCartProducts(int uid) {
        Session session = getSessionFactory();
        session.beginTransaction();
        Query query = session.createQuery(" from Cart where uid = " + uid);
        List<Cart> cartList = query.list();

        return cartList;
    }

    @Override
    public List<Cart> getMyCartProducts(List<Cart> cart) {
        Session session = getSessionFactory();
        session.beginTransaction();
        Iterator<Cart> iterator = cart.iterator();
        while (iterator.hasNext()){
            Cart next = iterator.next();
            cart.add(next);
            System.out.println("取出的商品 : " + next.getSellProduct().getSellName());
        }
        return cart;
    }

    @Override
    public void altCartProduct(User user, int buyQuantity, int sid) {
        Session session = getSessionFactory();
        session.beginTransaction();
        Query query = session.createQuery("update  Cart set buyQuantity = ? where sid = ? and uid = ?");
        query.setParameter(0,buyQuantity);
        query.setParameter(1,sid);
        query.setParameter(2,user.getUid());
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropCartProduct(SellProduct sellProduct,User user) {
        Session session = getSessionFactory();
        session.beginTransaction();
        Query query = session.createQuery("delete from Cart where sid = ? and uid = ?");
        query.setParameter(0,sellProduct.getSid());
        query.setParameter(1,user.getUid());
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
