package com.ping.myBuyCustomer;

import com.ping.myProduct.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyCustomerService {
    @Autowired
    BuyCustomerDao buyCustomerDao;

    public void dropMyProduct(Product product) {
        System.out.println("-------------------");
        System.out.println("準備提交刪除事務");
        System.out.println("準備刪除商品 ID  :"+ product.getProId());
        System.out.println("準備刪除商品名稱 :" + product.getProName());
        buyCustomerDao.dropMyProduct(product);
        System.out.println("刪除事務成功");
        System.out.println("-------------------");
    }

    //進入數據庫完成後，確認事務提交成功
    public BuyCustomer saveBuyCustomer(BuyCustomer buyCustomer, Product product) {
        System.out.println("-------------------");
        System.out.println("準備提交事務");
        buyCustomerDao.saveBuyCustomer(buyCustomer, product);
        System.out.println("提交事務成功");
        System.out.println("數據庫buyProduct表已經添加完畢");
        System.out.println("此次存取數據 : ");
//        System.out.println("ID : " + buyCustomer.getBuyId());
//        System.out.println("購買商品 : " + buyCustomer.getProduct().getProName());
//        System.out.println("購買客戶 : " + buyCustomer.getCustomer().getCusName());
        System.out.println("購買數量 : " + buyCustomer.getBuyNum());
        System.out.println("-------------------");
        return buyCustomer;
    }


    public List<BuyCustomer> showBuyProduct(int cusId) {
        System.out.println("-------------------");
        System.out.println("準備提取客戶購買的所有商品");
        System.out.println("提取客戶ID : " + cusId);
        List<BuyCustomer> buyCustomerList = buyCustomerDao.showBuyProduct(cusId);
        System.out.println("購買商品列表數量 : " + buyCustomerList);
        System.out.println("-------------------");
        return buyCustomerList;
    }

}
