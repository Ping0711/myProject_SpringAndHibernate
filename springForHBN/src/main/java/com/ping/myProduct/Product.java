package com.ping.myProduct;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int proId ;
    @Column(nullable = false)
    private String proName;
    @Column(nullable = false)
    private int proPrice;
    @Column(nullable = false)
    private String proPicture;

    public Product() {
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProPrice() {
        return proPrice;
    }

    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }

    public String getProPicture() {
        return proPicture;
    }

    public void setProPicture(String proPicture) {
        this.proPicture = proPicture;
    }

}
