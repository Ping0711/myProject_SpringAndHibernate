package com.ping.MySellProduct;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ISellProduct {
    void save(SellProduct sellProduct, MultipartFile multipartFile) throws IOException;

    List<SellProduct> mySellProducts(int uid);

    boolean checkValue(SellProduct sellProduct);

    SellProduct findMySellProduct(int sid);

    void alterMySellProduct(SellProduct sellProduct);

    void dropMySellProduct(SellProduct mySellProduct);

    List<SellProduct> allSellProducts();
}
