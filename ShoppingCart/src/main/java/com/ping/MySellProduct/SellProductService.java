package com.ping.MySellProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SellProductService {
    @Autowired
    SellProductDao sellProductDao;


    public void save(SellProduct sellProduct, MultipartFile multipartFile) {
        System.out.println("-----------------------------------");
        System.out.println("準備建立上架商品至SellProduct表");
        try {
            sellProductDao.save(sellProduct, multipartFile);
        } catch (IOException e) {
            System.out.println("檔案不存在");
            e.printStackTrace();
        }
    }

    public List<SellProduct> mySellProducts(int uid) {
        System.out.println("-----------------------------------");
        System.out.println("找尋客戶販賣的商品");
        List<SellProduct> sellProducts = sellProductDao.mySellProducts(uid);
        System.out.println("客戶擁有商品數 : " + sellProducts.size());
        return sellProducts;
    }

    public boolean checkValues(SellProduct sellProduct) {
        System.out.println("-----------------------------------");
        System.out.println("檢查客戶上架商品資訊");
        System.out.println("客戶ID : " + sellProduct.getUser().getUid());
        String sellName = sellProduct.getSellName();
        int sellPrice = sellProduct.getSellPrice();
        int sellQuantity = sellProduct.getSellQuantity();
        String sellPicture = sellProduct.getSellPicture();
        if (sellName.isEmpty() || sellName.length() > 10) {
            return false;
        } else if (sellPrice <= 0 || sellPrice > 1000000) {
            return false;
        } else if (sellQuantity <= 0 || sellQuantity > 1000000) {
            return false;
        } else if (sellPicture.isEmpty()) {
            return false;
        } else if (sellProductDao.checkValue(sellProduct)) {
            System.out.println("用戶已經上架相同名稱物品");
            return false;
        }
        return true;
    }

    public SellProduct findMySellProduct(int sid) {
        System.out.println("-----------------------------------");
        System.out.println("被點選的商品");
        SellProduct sellProduct = sellProductDao.findMySellProduct(sid);
        System.out.println("商品ID : " + sellProduct.getSid());
        System.out.println("商品名稱 : " + sellProduct.getSellName());
        return sellProduct;
    }

    public void alterMySellProduct(SellProduct sellProduct) {
        System.out.println("-----------------------------------");
        System.out.println("準備修改商品");
        System.out.println("商品ID : " + sellProduct.getSid());
        sellProductDao.alterMySellProduct(sellProduct);
        System.out.println("修改商品成功");
    }

    public boolean checkAlterValues(SellProduct sellProduct) {
        System.out.println("-----------------------------------");
        System.out.println("確認修改值");
        String sellName = sellProduct.getSellName();
        int sellPrice = sellProduct.getSellPrice();
        int sellQuantity = sellProduct.getSellQuantity();
        if (sellName.equals("")) {
            System.out.println("商品名稱錯誤");
            return false;
        } else if (sellPrice <= 0 || sellQuantity <= 0 || sellPrice > 1000000 || sellQuantity > 1000000) {
            System.out.println("商品價錢或商品數量錯誤");
            return false;
        }
        return true;
    }

    public void dropMySellProduct(SellProduct mySellProduct) {
        System.out.println("-----------------------------------");
        System.out.println("移除選擇的商品");
        System.out.println("商品ID : " + mySellProduct.getSid());
        sellProductDao.dropMySellProduct(mySellProduct);
        System.out.println("移除完成");
    }

    public List<SellProduct> allSellProducts() {
        System.out.println("-----------------------------------");
        System.out.println("準備顯示所有商品");
        List<SellProduct> sellProductList = sellProductDao.allSellProducts();
        System.out.println("商品數量 : " + sellProductList.size());
        return sellProductList;
    }
}
