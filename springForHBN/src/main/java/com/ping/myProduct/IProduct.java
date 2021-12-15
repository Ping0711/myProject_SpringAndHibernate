package com.ping.myProduct;

import java.util.List;

public interface IProduct {
    public void saveFile(Product product);
    public List<Product> showProducts();
    public Product findId(int proId);
    public boolean checkProducts(int proId, int buyNum);
    public List<Product> showMyProducts(int cusId);
}
