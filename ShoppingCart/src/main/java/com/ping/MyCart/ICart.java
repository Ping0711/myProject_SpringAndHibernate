package com.ping.MyCart;

import com.ping.MySellProduct.SellProduct;
import com.ping.MyUser.User;

import java.util.List;

public interface ICart {
    void save(Cart cart);

    void dropBuyProduct(SellProduct mySellProduct);

    void dropAllMyCart(int uid);

    List<Cart> getMyCartProducts(int uid);

    List<Cart> getMyCartProducts(List<Cart> cart);

    void altCartProduct(User user, int buyQuantity, int sid);

    void dropCartProduct(SellProduct sellProduct,User user);
}
