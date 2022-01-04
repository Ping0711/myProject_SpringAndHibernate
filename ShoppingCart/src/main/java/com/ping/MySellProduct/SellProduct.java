package com.ping.MySellProduct;

import com.ping.MyCart.Cart;
import com.ping.MyUser.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sellProduct")
public class SellProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    @Column
    private String sellName;
    @Column
    private Integer sellPrice;
    @Column
    private Integer sellQuantity;
    @Column
    private String sellPicture;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "uid")
    private User user;
//    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
//    @JoinColumn(name = "sid")
//    private List<Cart> cart;

//    public List<Cart> getCart() {
//        return cart;
//    }
//
//    public void setCart(List<Cart> cart) {
//        this.cart = cart;
//    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }


    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Integer sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public String getSellPicture() {
        return sellPicture;
    }

    public void setSellPicture(String sellPicture) {
        this.sellPicture = sellPicture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
