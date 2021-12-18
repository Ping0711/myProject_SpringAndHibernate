package com.ping.myProduct;

import java.util.List;

public interface IProduct {
    public void saveFile(Product product);
<<<<<<< HEAD

    public List<Product> showProducts();

    public Product findId(int proId);

    public boolean checkProducts(int proId, int buyNum);

    public List<Product> showMyProducts(int cusId);

    public void alterMyProduct(Product product, int altNum);

    void dropMyProduct(Product product);
=======
    public List<Product> showProducts();
    public Product findId(int proId);
    public boolean checkProducts(int proId, int buyNum);
    public List<Product> showMyProducts(int cusId);
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
}
