package com.ping.Controller;

import com.ping.myCustomer.Customer;
import com.ping.myCustomer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping
public class DispatcherController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/signIn")
    public String signIn(HttpServletRequest request,
                         ModelMap modelMap,
                         Customer customer) {
        // 取得此次表單所有 value，各別賦值
        customer.setCusName(request.getParameter("name"));
        customer.setCusPhone(request.getParameter("phone"));
        customer.setCusAddress(request.getParameter("address"));
        // checkPhone 做進一步判定(電話如果重複，返回失敗頁面)
        if (customerService.checkPhone(customer.getCusPhone())) return "ErrorPage";
        // 提交到Dao 做資料庫作持久層變化
        customerService.signIn(customer);
        return "signOK";
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
