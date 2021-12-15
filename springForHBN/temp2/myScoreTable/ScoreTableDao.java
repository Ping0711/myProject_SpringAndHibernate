package com.ping.myScoreTable;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ScoreTableDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<ScoreTable> findAll() {
        ScoreTable scoreTable;
        List<ScoreTable> list = new ArrayList();
        Session session = sessionFactory.openSession();
        String sql = "select * from scoreTable ;";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        List total = sqlQuery.list();
        for (int count = 1; count <= total.size(); count++) {
            scoreTable = session.get(ScoreTable.class, count);
            list.add(scoreTable);
        }
        session.close();
        return list;
    }

    public List<ScoreTable> findByChi() {
        ScoreTable scoreTable;
        List<ScoreTable> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        String sql = "select * from scoreTable order by chi;";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        List total = sqlQuery.list();
        for (int count = 1; count <= total.size(); count++) {
            scoreTable = session.get(ScoreTable.class, count);
            list.add(scoreTable);
        }
        session.close();
        for (int i = 0; i < list.size() - 1; i++) {
            ScoreTable tmp;
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getChi() > list.get(j + 1).getChi()) {  //重複比較 第i列 成績
                    tmp = list.get(j);
                    list.remove(j);
                    list.add(j + 1, tmp);
                    list.set(j + 1, tmp);
                }
            }
        }

        return list;
    }

    public List<ScoreTable> findByEng() {
        ScoreTable scoreTable;
        List<ScoreTable> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        String sql = "select * from scoreTable order by eng;";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        List total = sqlQuery.list();
        for (int count = 1; count <= total.size(); count++) {
            scoreTable = session.get(ScoreTable.class, count);
            list.add(scoreTable);
        }
        session.close();
        for (int i = 0; i < list.size() - 1; i++) {
            ScoreTable tmp;
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).getEng() > list.get(j + 1).getEng()) {  //重複比較 第i列 成績
                    tmp = list.get(j);
                    list.remove(j);
                    list.add(j + 1, tmp);
                    list.set(j + 1, tmp);
                }
            }

        }

        return list;
    }


}


