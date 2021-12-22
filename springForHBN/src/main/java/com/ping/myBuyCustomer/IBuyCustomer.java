package com.ping.myBuyCustomer;

import com.ping.myProduct.Product;

import java.util.List;

public interface IBuyCustomer {
    public BuyCustomer saveBuyCustomer(BuyCustomer buyCustomer, Product product);

    public List<BuyCustomer> showBuyProduct(int cusId);

    public void dropMyProduct(Product product);

    public void inAlterMyProduct(String button, String proId, String alterNum);

    void dropMyProductAll(int cusId);
}
