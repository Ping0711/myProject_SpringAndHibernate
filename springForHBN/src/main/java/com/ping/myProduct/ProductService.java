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

    public String saveFile(MultipartFile file, Product product) throws IOException, InterruptedException {
        String name = UUID.randomUUID().toString().replaceAll("-", ""); // UUTD產生亂碼後 去掉亂碼中 "-" 符號
        String fileName = file.getOriginalFilename(); // 取得上傳的file的名稱
        String ext = FilenameUtils.getExtension(fileName); // 取得上傳file的副檔名(ex: .jpg .png)
        String proPicture = name + "." + ext; //組合 亂碼+file名+副檔名 ex: UUTDRandomNumberFileName.jpg
        //複製file
        file.transferTo(new File("C:\\Users\\MSI\\Desktop\\springForHBN\\src\\main\\webapp\\WEB-INF\\images\\"+proPicture));
        file.transferTo(new File("C:\\Users\\MSI\\Desktop\\springForHBN\\target\\springForHBN\\WEB-INF\\images\\"+proPicture));
//        file.transferTo(new File("C:\\Users\\MSI\\Desktop\\springForHBN\\src\\main\\webapp\\WEB-INF\\images\\"+proPicture));
//        BufferedOutputStream outFile = new BufferedOutputStream(new FileOutputStream("C:\\Users\\MSI\\Desktop\\springForHBN\\src\\main\\webapp\\WEB-INF\\images\\" + name + "." + ext));
//        outFile.write(file.getBytes());
//        outFile.flush();
//        outFile.close();
        /*
        設定資料庫中 proPicture 路徑名稱
        將資料送進資料庫
         */
        product.setProPicture("images/" + proPicture);
        productDao.saveFile(product);
        return "images/" + proPicture;
    }

    public List<Product> showProducts() {
        return productDao.showProducts();
    }
}
