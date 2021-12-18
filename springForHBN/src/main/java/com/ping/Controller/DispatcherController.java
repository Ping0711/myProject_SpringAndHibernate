package com.ping.Controller;

import com.ping.myBuyCustomer.BuyCustomer;
import com.ping.myBuyCustomer.BuyCustomerService;
import com.ping.myCustomer.Customer;
import com.ping.myCustomer.CustomerService;
import com.ping.myProduct.Product;
import com.ping.myProduct.ProductService;
<<<<<<< HEAD
import com.sun.scenario.effect.impl.prism.PrDrawable;
=======
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
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
<<<<<<< HEAD
    @Autowired
    BuyCustomerService buyCustomerService;
=======
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da

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
<<<<<<< HEAD
=======

>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
        //獲取表單內屬性賦值，存入資料庫
        String proName = request.getParameter("proName");
        int proPrice = Integer.parseInt(request.getParameter("proPrice"));
        int proNum = Integer.parseInt(request.getParameter("proNum"));
        product.setProName(proName);
        product.setProPrice(proPrice);
        product.setProNum(proNum);
<<<<<<< HEAD
        product.setCustomer(customer);
=======
        product.setCusId(customer.getCusId());
        product.setCusName(customer.getCusName());
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
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
<<<<<<< HEAD
        List<Product> productList = productService.showProducts();
        for (Product product :
                productList) {
            System.out.println("商城ID : " + product.getProId());
        }
        modelMap.addAttribute("productList", productList);
        return "showProductPage";
    }

    //商品介紹
    @RequestMapping("/infoProduct")
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
        return "infoProductPage";
    }

    //我賣的商品
    @RequestMapping("/myProduct")
    public String myProduct(ModelMap modelMap, HttpServletRequest request, Customer customer,
                            Product product) {
        String findMyProduct = request.getParameter("findMyProduct"); // 分析進入請求
        System.out.println("in to myProduct");
        System.out.println("此次請求 : " + findMyProduct);
        int cusId = Integer.parseInt(request.getParameter("cusId")); //登入客戶ID
        customer = customerService.findById(cusId); // 用客戶ID 找到客戶表相關資料
        List<Product> myProductsList;

        if (findMyProduct.equals("findMyProduct")) {  // 如果從歡迎頁面登入
            myProductsList = productService.showMyProduct(customer.getCusId()); //用客戶ID 尋找客戶的販賣商品
            modelMap.addAttribute("customer", customer);
            modelMap.addAttribute("myProductsList", myProductsList);
            return "myProductPage";
        } else if (findMyProduct.equals("alter")) {     //如果從按鈕方法進入
            System.out.println("in alter");
            String button = request.getParameter("button"); // 取得按下的按鈕
            String alterNum = request.getParameter("alterNum"); // 取得輸入數字
            System.out.println("選擇的按鈕 : " + button);
            System.out.println("輸入的數量 : " + alterNum);
            int proId = Integer.parseInt(request.getParameter("proId")); //欲更改的商品ID
            product = productService.findId(proId); // 以商品ID尋找商品表相關資料
            if(alterNum.equals("") || Integer.parseInt(alterNum) <= 0) {
                myProductsList = productService.showMyProduct(customer.getCusId()); // 輸入錯誤的數量時，返回頁面
                modelMap.addAttribute("customer", customer);
                modelMap.addAttribute("myProductsList", myProductsList);
                System.out.println("輸入錯誤 返回頁面");
                return "myProductPage";
            }
            //決定更改選擇
            if (button.equals("修改")) {
                System.out.println("進入修改方法");
                productService.alterMyProduct(product, Integer.parseInt(alterNum));
                myProductsList = productService.showMyProduct(customer.getCusId()); //修改或移除後 重新用客戶ID 尋找客戶的販賣商品
                modelMap.addAttribute("customer", customer);
                modelMap.addAttribute("myProductsList", myProductsList);
                return "myProductPage";
            } else if (button.equals("移除") ) {
                System.out.println("進入移除方法");
                buyCustomerService.dropMyProduct(product);
                productService.dropMyProduct(product);
                myProductsList = productService.showMyProduct(customer.getCusId()); //修改或移除後 重新用客戶ID 尋找客戶的販賣商品
                modelMap.addAttribute("customer", customer);
                modelMap.addAttribute("myProductsList", myProductsList);
                return "myProductPage";
            }
            myProductsList = productService.showMyProduct(customer.getCusId()); //修改或移除後 重新用客戶ID 尋找客戶的販賣商品
            modelMap.addAttribute("customer", customer);
            modelMap.addAttribute("myProductsList", myProductsList);
            return "myProductPage";
        }
        modelMap.addAttribute("customer", customer); //如果沒進入方法 回到客戶功能
        return "welcomeCustomerPage";
    }
    //加入購物車
    @RequestMapping("/addMyCart")
    public String addMuCart(Customer customer, Product product, BuyCustomer buyCustomer,
                            HttpServletRequest request, ModelMap modelMap) {
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);
        // ---取得此次表單id 將資料庫id相同的內容裝進product裡
        int proId = Integer.parseInt(request.getParameter("proId"));
        product = productService.findId(proId);
        // ---取輸入的購買數量 並進入資料庫儲存
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));
        System.out.println("取得購買數量 : " + buyNum);
        buyCustomer.setCustomer(customer); //買家資訊
        buyCustomer.setProduct(product);//商品資訊
        buyCustomer.setBuyNum(buyNum);  //購買數量
        BuyCustomer saveBuyCustomer = buyCustomerService.saveBuyCustomer(buyCustomer, product);

        modelMap.addAttribute("saveBuyCustomer", saveBuyCustomer);
        /*
            雖然前端有先確認一次輸入數量與庫存量
            可是如果別人剛好買走，可以確認商品庫存是否足夠

        if (!productService.checkProducts(product.getProId(), buyNum)) {
            System.out.println("發現庫存不足");
            System.out.println("----------------------------");
            modelMap.addAttribute("customer", customer);
            modelMap.addAttribute("buyError", "真抱歉! 商品已經被買光拉~");
            modelMap.addAttribute("product", product);
            return "infoProductPage";
        }
        */
        modelMap.addAttribute("product", product);
        return "infoProductPage";
    }

    //我的購物車
    @RequestMapping("/checkMyCart")
    public String myCart(HttpServletRequest request, Product product, ModelMap modelMap, Customer customer) {
=======
        List<Product> list = productService.showProducts();
        modelMap.addAttribute("product", list);
        return "showProductPage";
    }

    //商品介紹
    @RequestMapping("/buyProduct")
    public String buyProduct(ModelMap modelMap, HttpServletRequest request, Customer customer,
                             Product product) {
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
        // ---取得此次表單id 將資料庫id相同的內容裝進customer裡
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        modelMap.addAttribute("customer", customer);
<<<<<<< HEAD
        List<BuyCustomer> buyCustomerList = buyCustomerService.showBuyProduct(customer.getCusId());
        modelMap.addAttribute("buyCustomerList", buyCustomerList);
        return "myCart";
=======
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
>>>>>>> e06b6c9b6e9d84400d8a788d3e02b1d03a5fe8da
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
