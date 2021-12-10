package com.ping.Controller;

import com.ping.myCustomer.Customer;
import com.ping.myCustomer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
@RequestMapping
public class DispatcherController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request,
                           ModelMap modelMap,
                           Customer customer) {
        // 取得此次表單所有 value，各別賦值
        customer.setCusName(request.getParameter("name"));
        customer.setCusPhone(request.getParameter("phone"));
        customer.setCusAddress(request.getParameter("address"));
        // checkPhone 做進一步判定(電話如果重複，返回失敗頁面)
        if (customerService.checkPhone(customer.getCusPhone())) {
            modelMap.addAttribute("registerError", "已有相同號碼註冊! \n手機號碼 : \t" + customer.getCusPhone());
            return "ErrorPage";
        }
        // 提交到Dao 做資料庫作持久層變化
        customerService.register(customer);
        // view 顯示註冊成功後的customer資料
        modelMap.addAttribute("cusRegister", customer);
        return "registerOKPage";
    }

    @RequestMapping("/signIn")
    public String signIn(Customer customer, HttpServletRequest request, ModelMap modelMap) {
        // ---取得此次表單CusName,setCusPhone
        customer.setCusName(request.getParameter("cusName"));
        customer.setCusPhone(request.getParameter("cusPhone"));
        /*
        驗證資料庫中 cusName,cusPhone是否相同
        (如果不相同，返回失敗頁面)
         */
        if (customerService.signIn(customer)) {
            modelMap.addAttribute("singInError", "會員姓名或會員手機不同! \n您輸入的為 : " + customer.getCusName() + "\t" + customer.getCusPhone());
            return "ErrorPage";
        }
        // view 顯示通過驗證成功後的customer資料
        modelMap.addAttribute("cusSingIN", customer);
        return "signInOKPage";
    }

    @RequestMapping("/checkProduct")
    public String checkProduct(ModelMap modelMap) {
        modelMap.addAttribute("s", "check");
        return "checkProductPage";
    }

    @RequestMapping("/newProduct")
    public String newProduct(ModelMap modelMap) {
        modelMap.addAttribute("s", "new");
        return "checkProductPage";
    }
}
