package com.ping.myProduct;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;
    //新增商品資料
    public String saveFile(MultipartFile file, Product product) throws IOException, InterruptedException {
        String name = UUID.randomUUID().toString().replaceAll("-", ""); // UUTD產生亂碼後 去掉亂碼中 "-" 符號
        String fileName = file.getOriginalFilename(); // 取得上傳的file的名稱
        String ext = FilenameUtils.getExtension(fileName); // 取得上傳file的副檔名(ex: .jpg .png)
        String proPicture = name + "." + ext; //組合 亂碼+file名+副檔名 ex: UUTDRandomNumberFileName.jpg
        //複製file
        file.transferTo(new File("C:\\Users\\MSI\\Desktop\\springForHBN\\src\\main\\webapp\\WEB-INF\\images\\"+proPicture));
        file.transferTo(new File("C:\\Users\\MSI\\Desktop\\springForHBN\\target\\springForHBN\\WEB-INF\\images\\"+proPicture));
        /*
        設定資料庫中 proPicture 路徑名稱
        將資料送進資料庫
         */
        product.setProPicture("images/" + proPicture);
        productDao.saveFile(product);
        return "images/" + proPicture;
    }
    //從資料庫拿取購物商城所有資料
    public List<Product> showProducts() {
        List<Product> productList = productDao.showProducts();
        System.out.println("---------------------------");
        System.out.println("資料庫內擁有的商品數 : "+productList.size());
        for (Product product:
             productList) {
            System.out.println("商品ID : " + product.getProId());
        }
        System.out.println("---------------------------");
        return productList;
    }
    //由商品id 從資料庫找到商品欄位所有資料
    public Product findId(int proId) {
        Product product = productDao.findId(proId);
        System.out.println("---------------------------");
        System.out.println("商品內容");
        System.out.println("ID :"+product.getProId());
        System.out.println("照片路徑 :"+product.getProPicture());
        System.out.println("商品名稱 :"+product.getProName());
        System.out.println("商品價格 :"+product.getProPrice());
        System.out.println("商品數量 :"+product.getProNum());
        System.out.println("---------------------------");
        return product;
    }
    //檢查想加入購物車的商品，庫存量夠不夠
    public boolean checkProducts(int proId,int buyNum){
        System.out.println("準備檢查庫存");
        boolean flag = productDao.checkProducts(proId,buyNum);
        System.out.println("----------------------------");
        System.out.println("商品編號 : "+proId);
        System.out.println("欲購買數量 : "+buyNum);
        System.out.println("商品足夠顯示 true  " );
        System.out.println("商品不夠顯示 false  " );
        System.out.println("顯示  : " +flag);
        System.out.println("----------------------------");
        return flag;
    }

    public List<Product> showMyProduct(int cusId) {
        System.out.println("----------------------------");
        System.out.println("尋找會員新增商品列表中...");
        List<Product> productList = productDao.showMyProducts(cusId);
        System.out.println("會員新增的商品列表數 : "+productList.size());
        System.out.println("各商品ID : ");
        for (Product product:
             productList) {
            System.out.println("ID :" + product.getProId());
        }
        System.out.println("----------------------------");
        return productList;
    }


    public Product inAlterMyProduct(String button, String alterNum,String alterPrice,Product product) {
        System.out.println("----------------------------");
        System.out.println("選擇的按鈕 : " + button);
        System.out.println("想更改的商品ID : " + product.getProId());
        System.out.println("輸入的數量 : " + alterNum);
        System.out.println("輸入的金額 : " + alterPrice);
        productDao.inAlterMyProduct(button,alterNum,alterPrice,product);
        System.out.println("更改動作結束 ! " );
        System.out.println("----------------------------");
        return product;
    }
}
