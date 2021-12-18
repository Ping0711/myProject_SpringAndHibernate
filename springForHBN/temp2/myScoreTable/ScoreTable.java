package com.ping.myScoreTable;

import javax.persistence.*;

@Entity
@Table(name = "scoreTable")
public class ScoreTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @OrderBy
    private int chi;
    @Column
    private int eng;
    @Column
    private int math;
    @Column
    private int EOne;
    @Column
    private int ETwo;
    @Column
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChi() {
        return chi;
    }

    public void setChi(int chi) {
        this.chi = chi;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEOne() {
        return EOne;
    }

    public void setEOne(int EOne) {
        this.EOne = EOne;
    }

    public int getETwo() {
        return ETwo;
    }

    public void setETwo(int ETwo) {
        this.ETwo = ETwo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ScoreTable{" +
                "id=" + id +
                ", chi=" + chi +
                ", eng=" + eng +
                ", math=" + math +
                ", EOne=" + EOne +
                ", ETwo=" + ETwo +
                ", total=" + total +
                '}';
    }
}
