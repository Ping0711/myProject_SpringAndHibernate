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
import java.util.*;

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
        // ---獲取登入時表單的值
        customer.setCusName(request.getParameter("cusName"));
        customer.setCusPhone(request.getParameter("cusPhone"));
        /*
        驗證資料庫中 cusName,cusPhone是否相同
        (如果不相同，返回失敗頁面)
         */
        if (!customerService.checkCus(customer)) {
            modelMap.addAttribute("singInError", "會員姓名或會員手機錯誤! \n您輸入的為 : " + customer.getCusName() + "\t" + customer.getCusPhone());
            return "ErrorPage";
        }
        Customer customer1 = customerService.signIn(customer);
        // view 顯示通過驗證成功後的customer資料
        modelMap.addAttribute("customer", customer1);
        return "welcomeCustomerPage";
    }

    //客戶功能
    @RequestMapping("welcomeCustomer")
    public String welcomeCustomer(Customer customer, HttpServletRequest request, ModelMap modelMap) {
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);
        return "welcomeCustomerPage";
    }

    //前往新增商品頁面
    @RequestMapping(value = "/newProduct", method = RequestMethod.POST)
    public String newProduct(ModelMap modelMap, Customer customer, HttpServletRequest request) {
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
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
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);

        //獲取表單內屬性賦值，存入資料庫
        String proName = request.getParameter("proName");
        int proPrice = Integer.parseInt(request.getParameter("proPrice"));
        int proNum = Integer.parseInt(request.getParameter("proNum"));
        product.setProName(proName);
        product.setProPrice(proPrice);
        product.setProNum(proNum);
        product.setCusId(customer.getCusId());
        product.setCusName(customer.getCusName());
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
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);
        //取得所有資料庫資料 並顯示
        List<Product> list = productService.showProducts();
        modelMap.addAttribute("product", list);
        return "showProductPage";
    }

    //商品介紹
    @RequestMapping("/buyProduct")
    public String buyProduct(ModelMap modelMap, HttpServletRequest request, Customer customer,
                             Product product) {
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);
        // ---取得此次表單id 將資料庫id相同的內容裝進product裡
        int proId = Integer.parseInt(request.getParameter("proId"));
        product = productService.findId(proId);

        modelMap.addAttribute("product", product);
        return "buyProductPage";
    }

    @RequestMapping("/myProduct")
    public String myProduct(ModelMap modelMap, HttpServletRequest request, Customer customer,
                            Product product){
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);
        List<Product> productList = productService.showMyProduct(customer.getCusId());
        modelMap.addAttribute("productList",productList);
        return "myProductPage";
    }
    //加入購物車
    @RequestMapping("/addMyCart")
    public String addMuCart(Customer customer, Product product, HttpServletRequest request, ModelMap modelMap) {
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);
        // ---取得此次表單id 將資料庫id相同的內容裝進product裡
        int proId = Integer.parseInt(request.getParameter("proId"));
        product = productService.findId(proId);
        // ---取輸入的購買數量
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));
        System.out.println("取得購買數量 : " + buyNum);
        /*
            雖然前端有先確認一次輸入數量與庫存量
            可是如果別人剛好買走，可以確認商品庫存是否足夠
         */
        if (!productService.checkProducts(product.getProId(), buyNum)) {
            System.out.println("發現庫存不足");
            System.out.println("----------------------------");
            modelMap.addAttribute("customer", customer);
            modelMap.addAttribute("buyError", "真抱歉! 商品已經被買光拉~");
            modelMap.addAttribute("product", product);
            return "buyProductPage";
        }
        modelMap.addAttribute("product", product);
        return "buyProductPage";
    }

    //我的購物車
    @RequestMapping("/checkMyCart")
    public String myCart(HttpServletRequest request, Product product, ModelMap modelMap, Customer customer) {
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        return "myCart";
    }


}
