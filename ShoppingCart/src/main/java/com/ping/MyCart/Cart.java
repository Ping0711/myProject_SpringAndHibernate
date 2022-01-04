package com.ping.MyCart;

import com.ping.MySellProduct.SellProduct;
import com.ping.MyUser.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "sid" )
    private SellProduct sellProduct;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "uid" )
    private User user;
    private Integer buyQuantity;

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public SellProduct getSellProduct() {
        return sellProduct;
    }

    public void setSellProduct(SellProduct sellProduct) {
        this.sellProduct = sellProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    public Integer getBuyQuantity() {
//        return buyQuantity;
//    }
//
//    public void setBuyQuantity(Integer buyQuantity) {
//        this.buyQuantity = buyQuantity;
//    }
}
