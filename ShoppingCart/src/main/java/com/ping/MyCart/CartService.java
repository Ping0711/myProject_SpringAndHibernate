package com.ping.MyCart;

import com.ping.MySellProduct.SellProduct;
import com.ping.MyUser.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartDao cartDao;


    public void save(Cart cart) {
        System.out.println("-------------------");
        System.out.println("準備將商品放入購物車");
        cartDao.save(cart);
        System.out.println("成功放入");
    }

    public void dropBuyProduct(SellProduct mySellProduct) {
        System.out.println("-------------------");
        System.out.println("準備將商品移除購物車");
        cartDao.dropBuyProduct(mySellProduct);
        System.out.println("成功移除");
    }


    public void dropAllMyCart(int uid) {
        System.out.println("-------------------");
        System.out.println("準備清除購物車");
        cartDao.dropAllMyCart(uid);
        System.out.println("清除完畢");
    }


    public List<Cart> getMyCartProducts(int uid) {
        System.out.println("-------------------");
        System.out.println("查看購物車內商品");
        List<Cart> cartList = cartDao.getMyCartProducts(uid);
        System.out.println("購物車內商品數量 : " + cartList.size());
        Iterator<Cart> iterator = cartList.iterator();
        while (iterator.hasNext()) {
            Cart next = iterator.next();
            System.out.println("商品" + next.getSellProduct().getSellName());
        }
        return cartList;
    }

    public List<Cart> getMyCartProducts(List<Cart> cart) {
        System.out.println("-------------------");
        System.out.println("查看購物車內商品");
        List<Cart> cartList = cartDao.getMyCartProducts(cart);
        System.out.println("購物車內商品數量 : " + cartList.size());
        return cartList;
    }

    public boolean checkMyCart(int uid, int sid) {
        System.out.println("-------------------");
        System.out.println("查看商品是否已加入購物車");
        List<Cart> myCartProducts = cartDao.getMyCartProducts(uid);
        System.out.println("購物車內商品數量 : " + myCartProducts.size());
        Iterator<Cart> iterator = myCartProducts.iterator();
        while (iterator.hasNext()) {
            int proId = iterator.next().getSellProduct().getSid();
            System.out.println("購物車內商品ID : " + proId);
            if (proId == sid) {
                return true;
            }
        }
        System.out.println("無重複商品");
        return false;
    }

    public void altCartProduct(User user, int buyQuantity, int sid) {
        System.out.println("-------------------");
        System.out.println("修改購物車內數量");
        cartDao.altCartProduct(user, buyQuantity, sid);

        System.out.println("修改完成");
    }

    public void dropCartProduct(SellProduct sellProduct, User user) {
        System.out.println("-------------------");
        System.out.println("準備將商品移除購物車");
        cartDao.dropCartProduct(sellProduct, user);
        System.out.println("成功移除");
    }

    public int culTotal(List<Cart> myCartProducts) {
        System.out.println("-------------------");
        System.out.println("計算總額");
        int total = 0;
        Iterator<Cart> iterator = myCartProducts.iterator();
        while (iterator.hasNext()) {
            Cart cart = iterator.next();
            int buyQuantity = cart.getBuyQuantity();
            int sellPrice = cart.getSellProduct().getSellPrice();
            total += (buyQuantity * sellPrice);
        }
        System.out.println("總額為 : " + total);
        return total;
    }
}
