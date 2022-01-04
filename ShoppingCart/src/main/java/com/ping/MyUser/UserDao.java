package com.ping.MyUser;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao implements IUser {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return sessionFactory.openSession();
    }

    public void saveUser(User user) {
        Session session = getSessionFactory();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public boolean checkUser(String username, String password) {
        Session session = getSessionFactory();
        session.beginTransaction();
        String sql = "select * from User where username = ? and password = ?";
        SQLQuery SQLQuery = session.createSQLQuery(sql);
        SQLQuery.setParameter(0,username);
        SQLQuery.setParameter(1,password);
        List list = SQLQuery.list();
        boolean flag = list.size() > 0;
        session.close();
        return flag;
    }

    public User SingIn(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where username = ? and password = ?");
        query.setParameter(0,user.getUsername());
        query.setParameter(1,user.getPassword());
        List<User> userList = query.list();
        user = session.get(User.class,userList.get(0).getUid());
        return user;
    }

    @Override
    public User SingIn(int uid) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class,uid);
        session.close();
        return user;
    }
}
