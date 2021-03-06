package com.ping.myProduct;

import java.util.List;

public interface IProduct {
    public void saveFile(Product product);

    public List<Product> showProducts();

    public Product findId(int proId);

    public boolean checkProducts(int proId, int buyNum);

    public List<Product> showMyProducts(int cusId);

    public void alterMyProduct(Product product, int altNum);

    public void dropMyProduct(Product product);

    public Product inAlterMyProduct(String button, String alterNum,String alterPrice, Product product);
}
