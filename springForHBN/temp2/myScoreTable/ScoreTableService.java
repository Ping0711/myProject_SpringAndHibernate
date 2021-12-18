package com.ping.myScoreTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreTableService {
    @Autowired
    ScoreTableDao scoreTableDao;
    public List<ScoreTable> findAll() {
        return scoreTableDao.findAll();
    }

    public List<ScoreTable> findByChi() {
        return scoreTableDao.findByChi();
    }
    public List<ScoreTable> findByEng() {
        return scoreTableDao.findByEng();
    }
}
