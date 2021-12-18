package com.ping.myBuyCustomer;

import com.ping.myCustomer.Customer;
import com.ping.myProduct.Product;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "buyCustomer")
public class BuyCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int buyId;
    @Column
    private int buyNum;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "buyProId" ,foreignKey = @ForeignKey(name = "buyId"))
    private Product Product;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn( name = "cusId")
    private Customer customer;

    public int getBuyId() {
        return buyId;
    }

    public void setBuyId(int buyId) {
        this.buyId = buyId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public com.ping.myProduct.Product getProduct() {
        return Product;
    }

    public void setProduct(com.ping.myProduct.Product product) {
        Product = product;
    }
}
