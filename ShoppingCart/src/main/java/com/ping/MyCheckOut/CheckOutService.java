package com.ping.MyCheckOut;

import com.ping.MyCart.Cart;
import com.ping.MySellProduct.SellProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CheckOutService {
    @Autowired
    CheckOutDao checkOutDao;

    public  boolean checkQuantity(Cart cart) {
        int buyQuantity = cart.getBuyQuantity();
        int sellQuantity = cart.getSellProduct().getSellQuantity();
        return sellQuantity >= buyQuantity;
    }

    public int culPrice(SellProduct sellProduct,int buyQuantity) {
        System.out.println("----------------------------------");
        System.out.println("計算單筆商品價格");
        int productTotal = 0 ;
        int productPrice = sellProduct.getSellPrice();
        productTotal=productPrice*buyQuantity;
        System.out.println("購物車內商品 : " + sellProduct.getSellName());
        System.out.println("商品單價 : " + sellProduct.getSellName());
        System.out.println("購買數量 : " + buyQuantity);
        System.out.println("單筆總額 : " + productTotal);
        return productTotal;
    }

    public void save(CheckOut checkOut) {
        System.out.println("----------------------------------");
        System.out.println("準備向各賣家送出訂單");
        checkOutDao.save(checkOut);
        System.out.println("送出訂單完成");
    }
}
