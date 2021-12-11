package com.ping.Controller;

import com.ping.myCustomer.Customer;
import com.ping.myCustomer.CustomerService;
import com.ping.myProduct.Product;
import com.ping.myProduct.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping
public class DispatcherController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    //註冊系統
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Customer customer, HttpServletRequest request, ModelMap modelMap) {
        // 取得此次表單所有 value，各別賦值
        customer.setCusName(request.getParameter("name"));
        customer.setCusPhone(request.getParameter("phone"));
        customer.setCusAddress(request.getParameter("address"));
        // checkPhone 做進一步判定(電話如果重複，返回失敗頁面)
        if (customerService.check(customer.getCusPhone())) {
            modelMap.addAttribute("registerError", "已有相同號碼註冊! \n手機號碼 : \t" + customer.getCusPhone());
            return "ErrorPage";
        }
        // 提交到Dao 做資料庫作持久層變化
        customerService.register(customer);
        // view 顯示註冊成功後的customer資料
        modelMap.addAttribute("customer", customer);
        return "registerOKPage";
    }

    //登入系統
    @RequestMapping("/signIn")
    public String signIn(Customer customer, HttpServletRequest request, ModelMap modelMap) {
        // ---取得此次表單CusName,setCusPhone
        customer.setCusName(request.getParameter("cusName"));
        customer.setCusPhone(request.getParameter("cusPhone"));
        modelMap.addAttribute("customer", customer);
        /*
        驗證資料庫中 cusName,cusPhone是否相同
        (如果不相同，返回失敗頁面)
         */
        if (customerService.signIn(customer)) {
            modelMap.addAttribute("singInError", "會員姓名或會員手機錯誤! \n您輸入的為 : " + customer.getCusName() + "\t" + customer.getCusPhone());
            return "ErrorPage";
        }
        // view 顯示通過驗證成功後的customer資料
        return "signInOKPage";
    }

    //前往新增商品頁面
    @RequestMapping(value = "/newProduct", method = RequestMethod.POST)
    public String newProduct(ModelMap modelMap, Customer customer, HttpServletRequest request) {
        customer.setCusName(request.getParameter("cusName"));
        customer.setCusPhone(request.getParameter("cusPhone"));

        modelMap.addAttribute("customer", customer);
        return "newProductPage";
    }

    //新增商品系統
    /*
      使用MultipartFile，表單一定要加enctype="multipart/form-data"，將傳來的file轉成二進制
      1.@RequestParam()對映表單中的file
        UUID 是一種生成亂碼的類，生成的亂碼像是-->[randomNumber-randomNumber-randomNumber-randomNumber]
     */
    @RequestMapping(value = "/forNewProduct", method = RequestMethod.POST)
    public String forNewProduct(Product product, Customer customer, HttpServletRequest request, ModelMap modelMap,
                                @RequestParam(value = "file", required = false) MultipartFile file)
            throws IOException, InterruptedException {
        //傳遞會員資料
        customer.setCusName(request.getParameter("cusName"));
        customer.setCusPhone(request.getParameter("cusPhone"));
        modelMap.addAttribute("customer", customer);

        //獲取表單內屬性賦值，存入資料庫
        String proName = request.getParameter("proName");
        int proPrice = Integer.parseInt(request.getParameter("proPrice"));
        product.setProName(proName);
        product.setProPrice(proPrice);
        /*  productService.saveFile()
            在方法裡 file複製到images裡 返回String 資料路徑
            並將file路徑 存進資料庫裡
         */
        String proPicture = productService.saveFile(file, product);
        System.out.println("picture 路徑 : " + proPicture);
        modelMap.addAttribute("product", product);
        return "newProductOKPage";
    }

    //購物商城
    @RequestMapping("/showProductPage")
    public String showProductPage(Customer customer, HttpServletRequest request, ModelMap modelMap) {
        customer.setCusName(request.getParameter("cusName"));
        customer.setCusPhone(request.getParameter("cusPhone"));
        modelMap.addAttribute("customer", customer);
        //取得所有資料庫資料 並顯示
        productService.showProducts();
        modelMap.addAttribute("product", productService.showProducts());
        return "showProductPage";
    }
}
