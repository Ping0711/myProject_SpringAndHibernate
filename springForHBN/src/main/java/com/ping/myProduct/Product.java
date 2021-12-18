package com.ping.myProduct;

<<<<<<< HEAD
import com.ping.myBuyCustomer.BuyCustomer;
import com.ping.myCustomer.Customer;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
=======
import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proId;
    @Column(nullable = false)
    private String proName;
    @Column(nullable = false)
    private int proPrice;
    @Column(nullable = false)
    private String proPicture;
    @Column(nullable = false)
    private int proNum;
<<<<<<< HEAD
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "cusId")
    private Customer customer;
=======
    @Column(nullable = false)
    private int cusId;
    @Column(nullable = false)
    private String cusName;

>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da

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

    public int getProNum() {
        return proNum;
    }

    public void setProNum(int proNum) {
        this.proNum = proNum;
    }

<<<<<<< HEAD
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
=======
    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
    }
}
