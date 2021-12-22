package com.ping.Controller;

import com.ping.myBuyCustomer.BuyCustomer;
import com.ping.myBuyCustomer.BuyCustomerService;
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
    @Autowired
    BuyCustomerService buyCustomerService;

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
        product.setCustomer(customer);
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
        List<Product> productList = productService.showProducts();
        modelMap.addAttribute("productList", productList);
        return "showProductPage";
    }

    //商品介紹
    @RequestMapping("/infoProduct")
    public String infoProduct(ModelMap modelMap, HttpServletRequest request, Customer customer,
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

    //加入購物車
    @RequestMapping("/addMyCart")
    public String addMyCart(Customer customer, Product product, BuyCustomer buyCustomer,
                            HttpServletRequest request, ModelMap modelMap) {
        // ---取輸入的購買數量 並進入資料庫儲存
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        System.out.println("取得購買數量 : " + buyNum);
        int proId = Integer.parseInt(request.getParameter("proId"));
        customer = customerService.findById(cusId);
        product = productService.findId(proId);
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
        return infoProduct(modelMap, request, customer, product);
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
            String alterNum = request.getParameter("alterNum"); // 取得修改數量
            String alterPrice = request.getParameter("alterPrice"); // 取得修改金額
            int proId = Integer.parseInt(request.getParameter("proId")); //欲更改的商品ID
            product = productService.findId(proId);         // 以商品ID尋找商品表相關資料
            productService.inAlterMyProduct(button, alterNum, alterPrice, product); //進入更改方法 判定按鈕做後續的動作
        }
        myProductsList = productService.showMyProduct(customer.getCusId()); //修改或移除後 重新用客戶ID 尋找客戶的販賣商品
        modelMap.addAttribute("myProductsList", myProductsList);
        modelMap.addAttribute("customer", customer); //如果沒進入方法 回到客戶功能
        return "myProductPage";
    }

    //我的購物車
    @RequestMapping("/checkMyCart")
    public String myCart(HttpServletRequest request, Product product, ModelMap modelMap, Customer customer, BuyCustomer buyCustomer) {
        int cusId = Integer.parseInt(request.getParameter("cusId")); //獲取會員ID
        customer = customerService.findById(cusId);                     //以會員ID從客戶表獲取資料
        List<BuyCustomer> buyCustomerList =
                buyCustomerService.showBuyProduct(customer.getCusId());   //以會員ID 查找購買的商品
        modelMap.addAttribute("buyCustomerList", buyCustomerList);
        modelMap.addAttribute("customer", customer);
        return "myCart";
    }

    //修改移除按鈕
    @RequestMapping("/alterProduct")
    public String alterProduct(HttpServletRequest request, Product product, ModelMap modelMap, Customer customer, BuyCustomer buyCustomer) {
        String proId = request.getParameter("proId");            //獲取修改商品ID
        String alterNum = request.getParameter("alterNum");     //獲取修改數量
        String choose = request.getParameter("choose");
        if (alterNum.equals("") || Integer.parseInt(alterNum) <= 0 || alterNum.isEmpty()) {
            System.out.println("輸入錯誤 返回頁面");
            modelMap.addAttribute("Error", "您輸入的數量有誤!");
            return myCart(request, product, modelMap, customer, buyCustomer);
        }
        buyCustomerService.alterBuyProduct(choose, proId, alterNum);
        return myCart(request, product, modelMap, customer, buyCustomer);
    }

    //移除按鈕
    @RequestMapping("/dropProduct")
    public String dropProduct(HttpServletRequest request, Product product, ModelMap modelMap, Customer customer, BuyCustomer buyCustomer) {
        int proId = Integer.parseInt(request.getParameter("proId"));
        product = productService.findId(proId);
        buyCustomerService.dropMyProduct(product);

        return myCart(request, product, modelMap, customer, buyCustomer);
    }
    //清除購物車按鈕
    @RequestMapping("/cleanCart")
    public String cleanCart(HttpServletRequest request, Product product, ModelMap modelMap, Customer customer, BuyCustomer buyCustomer) {
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        buyCustomerService.dropMyProductAll(cusId);
        return myCart(request, product, modelMap, customer, buyCustomer);
    }

    //結帳系統
    @RequestMapping("/checkOut")
    public String checkOut(HttpServletRequest request, Product product, ModelMap modelMap, Customer customer, BuyCustomer buyCustomer) {
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        int total;
        List<BuyCustomer> checkProductList;
        customer = customerService.findById(cusId); //以會員ID找尋客戶表
        checkProductList = buyCustomerService.showBuyProduct(customer.getCusId()); //查找購買會員的購物車內容
        total = buyCustomerService.culPrice(checkProductList); //計算購物車內商品的總額
        modelMap.addAttribute("total", total);
        modelMap.addAttribute("customer", customer);
        modelMap.addAttribute("checkProductList", checkProductList);
        return "checkOutPage";
    }
    //確認結帳按鈕
    @RequestMapping("/conFirm")
    public String conFirm(HttpServletRequest request, Product product, ModelMap modelMap,
                          Customer customer, BuyCustomer buyCustomer) {
        int cusId = Integer.parseInt(request.getParameter("cusId"));
        customer = customerService.findById(cusId);
        int totalPrice;
        List<BuyCustomer> buyCustomerList;
        buyCustomerList = buyCustomerService.showBuyProduct(cusId);
        totalPrice = buyCustomerService.culPrice(buyCustomerList); // 購物車內總額

//        checkOutService.findAll();
//        buyCustomerService.dropMyProductAll(cusId);
        return "checkOutOKPage";
    }
}
