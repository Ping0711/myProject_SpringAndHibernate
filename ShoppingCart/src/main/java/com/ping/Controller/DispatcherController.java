package com.ping.Controller;

import com.ping.MyCart.Cart;
import com.ping.MyCart.CartService;
import com.ping.MyCheckOut.CheckOut;
import com.ping.MyCheckOut.CheckOutService;
import com.ping.MySellProduct.SellProduct;
import com.ping.MySellProduct.SellProductService;
import com.ping.MyUser.User;
import com.ping.MyUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.JoinColumn;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping
public class DispatcherController {
    @Autowired
    UserService userService;
    @Autowired
    SellProductService sellProductService;
    @Autowired
    CartService cartService;
    @Autowired
    CheckOutService checkOutService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(SellProduct sellProduct, ModelMap modelMap, User user) {
        List<SellProduct> sellProductList = sellProductService.allSellProducts();
        modelMap.addAttribute("sellProductList", sellProductList);
        modelMap.addAttribute("user", user);
        return "indexPage";
    }

    //-------------------------------註冊頁面
    @RequestMapping("/RegisterPage")
    public String RegisterPage(ModelMap modelMap) {
        return "RegisterPage";
    }
    //-------------------------------驗證身分證
    @RequestMapping("/checkIDCard")
    public boolean checkIDCard(HttpServletRequest request, ModelMap modelMap, User user){
        String idCard =request.getParameter("IDCard");
        boolean flag = userService.checkIDCard(idCard);
        if(flag){
            return true;
        }
        System.out.println("idCard = "+ idCard);
        return false;
    }
    //-------------------------------註冊按鈕
    @RequestMapping("/SignUp")
    public String SignUp(HttpServletRequest request, ModelMap modelMap, User user) {
        boolean flag;
        flag = checkIDCard( request,  modelMap,  user);
        if(!flag){
            modelMap.addAttribute("Error","驗證身分證錯誤");
            return RegisterPage(modelMap);
        }
        //-----------------------------------------------------
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        //-----------------------------------------------------
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);
        //----------------------------------------------------- 檢視用戶輸入的資訊是否正確
        flag = userService.checkValues(user);
        if (!flag) {
            System.out.println("格式錯誤");
            modelMap.addAttribute("Error", "格式錯誤");
            return "RegisterPage";
        }
        //----------------------------------------------------- 確認是否有相同用戶
        flag = userService.checkUser(username, password);
        if (flag) {
            System.out.println("已註冊");
            modelMap.addAttribute("Error", "已註冊");
            return "RegisterPage";
        } else {
            System.out.println("即將註冊");
            userService.saveUser(user);
        }
        //-----------------------------------------------------
        user = userService.SignIn(user.getUid());
        modelMap.addAttribute("user", user);
        return welcomePage( user,  modelMap);
    }


    //-------------------------------登入頁面
    @RequestMapping("/signInPage")
    public String signInPage() {
        return "signInPage";
    }

    //-------------------------------登入按鈕
    @RequestMapping("/signIn")
    public String signIn(HttpServletRequest request, ModelMap modelMap, User user) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = userService.checkUser(username, password);
        if (!flag) {
            System.out.println("未註冊");
            modelMap.addAttribute("Error", "未註冊或輸入帳號密碼錯誤");
            return "signInPage";
        } else {
            System.out.println("進登入方法");
            user = userService.SignIn(user);
        }
        return welcomePage(user, modelMap);
    }

    //-------------------------------會員中心
    @RequestMapping(value = "/welcomePage")
    public String welcomePage(User user, ModelMap modelMap) {
        user = userService.SignIn(user.getUid());
        modelMap.addAttribute("user", user);
        return "welcomePage";
    }

    //-------------------------------購物商城
    @RequestMapping(value = "/shoppingMallPage")
    public String shoppingMallPage(User user, ModelMap modelMap) {
        user = userService.SignIn(user.getUid());
        List<SellProduct> sellProductList = sellProductService.allSellProducts();
        modelMap.addAttribute("sellProductList", sellProductList);
        modelMap.addAttribute("user", user);
        Iterator<SellProduct> sellProductIterator = sellProductList.iterator();
        while (sellProductIterator.hasNext()) {
            SellProduct sellProduct = sellProductIterator.next();
            int sellQuantity = sellProduct.getSellQuantity();
            if (sellQuantity == 0) {
                modelMap.addAttribute("massage", "已售完");
            }
        }
        return "shoppingMallPage";
    }

    //-------------------------------上架商品頁面
    @RequestMapping("/sellProductPage")
    public String sellProductPage(User user, ModelMap modelMap) {
        user = userService.SignIn(user.getUid());
        modelMap.addAttribute("user", user);
        return "sellProductPage";
    }

    //-------------------------------上架按鈕
    @RequestMapping("/sellProduct")
    public String sellProduct(User user, ModelMap modelMap, SellProduct sellProduct, HttpServletRequest request,
                              @RequestParam(value = "file") MultipartFile multipartFile) {
        user = userService.SignIn(user.getUid());
        modelMap.addAttribute("user", user);
        //-------------------------------------------------
        String sellName = request.getParameter("sellName");
        String sellPrice = request.getParameter("sellPrice");
        String sellQuantity = request.getParameter("sellQuantity");
        //-------------------------------------------------
        if (sellPrice.isEmpty() || sellQuantity.isEmpty()) {
            modelMap.addAttribute("reMind", "有未填資料或格式錯誤");
            return "sellProductPage";
        }
        sellProduct.setSellName(sellName);
        sellProduct.setSellPrice(Integer.parseInt(sellPrice));
        sellProduct.setSellQuantity(Integer.parseInt(sellQuantity));
        sellProduct.setSellPicture(multipartFile.getOriginalFilename());
        sellProduct.setUser(user);
        boolean flag = sellProductService.checkValues(sellProduct);
        if (!flag) {
            modelMap.addAttribute("reMind", "格式錯誤 或 相同名稱商品");
            return "sellProductPage";
        }
        //-------------------------------------------------
        sellProductService.save(sellProduct, multipartFile);
        return myProductPage(user, modelMap);
    }

    //-------------------------------我的商品
    @RequestMapping("/myProductPage")
    public String myProductPage(User user, ModelMap modelMap) {
        user = userService.SignIn(user.getUid());
        List<SellProduct> sellProductList;
        sellProductList = sellProductService.mySellProducts(user.getUid());
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("sellProductList", sellProductList);
        return "myProductPage";
    }

    //-------------------------------修改按鈕
    @RequestMapping("/alterMyProduct")
    public String alterMyProduct(User user, ModelMap modelMap, SellProduct sellProduct, HttpServletRequest request) {
        String sid = request.getParameter("sid");
        sellProduct = sellProductService.findMySellProduct(Integer.parseInt(sid));
        String sellName = request.getParameter("sellName");
        String sellPrice = request.getParameter("sellPrice");
        String sellQuantity = request.getParameter("sellQuantity");
        boolean flag;
        //-------------------------------------------------------------
        if (sellPrice.isEmpty() || sellQuantity.isEmpty() || sellName.isEmpty()) {
            modelMap.addAttribute("reMind", "資料未填值");
            return myProductPage(user, modelMap);
        }
        sellProduct.setSellName(sellName);
        sellProduct.setSellPrice(Integer.parseInt(sellPrice));
        sellProduct.setSellQuantity(Integer.parseInt(sellQuantity));
        //-------------------------------------------------------------
        flag = sellProductService.checkAlterValues(sellProduct);
        if (!flag) {
            modelMap.addAttribute("reMind", "資料內容錯誤");
            return myProductPage(user, modelMap);
        }
        //--------------------------------------------------------------
        sellProductService.alterMySellProduct(sellProduct);
        return myProductPage(user, modelMap);
    }

    //-------------------------------移除按鈕
    @RequestMapping("/dropMyProduct")
    public String dropMyProduct(User user, ModelMap modelMap, SellProduct sellProduct, HttpServletRequest request) {
        String sid = request.getParameter("sid");
        SellProduct mySellProduct = sellProductService.findMySellProduct(Integer.parseInt(sid));
        cartService.dropBuyProduct(mySellProduct);
        sellProductService.dropMySellProduct(mySellProduct);
        return myProductPage(user, modelMap);
    }

    //-------------------------------購物車
    @RequestMapping("/myCartPage")
    public String myCartPage(User user, ModelMap modelMap, HttpServletRequest request) {
        String uid = request.getParameter("uid");
        user = userService.SignIn(Integer.parseInt(uid));
        List<Cart> myCartProducts = cartService.getMyCartProducts(user.getUid());
//        user.setCart(myCartProducts);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("myCartProducts", myCartProducts);
//        modelMap.addAttribute("cart", cart);
        return "myCartPage";
    }

    //-------------------------------加入購物車按鈕
    @RequestMapping("/addMyCart")
    public String addMyCart(User user, ModelMap modelMap, HttpServletRequest request, Cart cart) {
        String sid = request.getParameter("sid");
        String uid = request.getParameter("uid");
        String buyQuantity = request.getParameter("buyQuantity");
        SellProduct mySellProduct;
        user = userService.SignIn(Integer.parseInt(uid));
        mySellProduct = sellProductService.findMySellProduct(Integer.parseInt(sid));
        //----------------------------------------------------
        boolean flag = cartService.checkMyCart(Integer.parseInt(uid), Integer.parseInt(sid));
        if (flag) {
            modelMap.addAttribute("reMind", "購物車中已有相同商品");
            return shoppingMallPage(user, modelMap);
        } else if (buyQuantity.isEmpty() || Integer.parseInt(buyQuantity) <= 0
                || Integer.parseInt(buyQuantity) > mySellProduct.getSellQuantity()) {
            modelMap.addAttribute("reMind", "請確認購買數量");
            return shoppingMallPage(user, modelMap);
        }
        //----------------------------------------------------
        cart.setSellProduct(mySellProduct);
        cart.setUser(user);
        cart.setBuyQuantity(Integer.parseInt(buyQuantity));
        cartService.save(cart);
        modelMap.addAttribute("reMind", "加入購物車成功!");
        return shoppingMallPage(user, modelMap);
    }

    //-------------------------------清空購物車按鈕
    @RequestMapping("/dropAllMyCart")
    public String dropMyAllCart(User user, HttpServletRequest request, ModelMap modelMap) {
        String uid = request.getParameter("uid");
        user = userService.SignIn(Integer.parseInt(uid));
        cartService.dropAllMyCart(user.getUid());
        return myCartPage(user, modelMap, request);
    }

    //-------------------------------購物車更改數量按鈕
    @RequestMapping("/altCartProduct")
    public String AltCartProduct(User user, HttpServletRequest request, ModelMap modelMap) {
        String uid = request.getParameter("uid");
        String sid = request.getParameter("sid");
        String buyQuantity = request.getParameter("buyQuantity");
        user = userService.SignIn(Integer.parseInt(uid));
        SellProduct mySellProduct = sellProductService.findMySellProduct(Integer.parseInt(sid));
        if (buyQuantity.isEmpty() || Integer.parseInt(buyQuantity) <= 0) {
            modelMap.addAttribute("reMind", "請確認修改數量");
            return myCartPage(user, modelMap, request);
        } else if (mySellProduct.getSellQuantity() < Integer.parseInt(buyQuantity)) {
            modelMap.addAttribute("reMind", "修改數量超過庫存了");
            return myCartPage(user, modelMap, request);
        }
        cartService.altCartProduct(user, Integer.parseInt(buyQuantity), Integer.parseInt(sid));
        return myCartPage(user, modelMap, request);
    }

    //-------------------------------購物車移除單個商品按鈕
    @RequestMapping("/dropCartProduct")
    public String dropCartProduct(User user, HttpServletRequest request, ModelMap modelMap) {
        String uid = request.getParameter("uid");
        String sid = request.getParameter("sid");
        user = userService.SignIn(Integer.parseInt(uid));
        SellProduct sellProduct = sellProductService.findMySellProduct(Integer.parseInt(sid));
        cartService.dropCartProduct(sellProduct, user);
        return myCartPage(user, modelMap, request);
    }

    //-------------------------------結帳網頁
    @RequestMapping("/checkOutPage")
    public String checkOutPage(User user, HttpServletRequest request, ModelMap modelMap) {
        String uid = request.getParameter("uid");
        user = userService.SignIn(Integer.parseInt(uid));
        List<Cart> myCartProducts = cartService.getMyCartProducts(user.getUid());
        int total = cartService.culTotal(myCartProducts);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("myCartProducts", myCartProducts);
        modelMap.addAttribute("total", total);
        return "checkOutPage";
    }

    //-------------------------------結帳按鈕
    @RequestMapping("/checkOut")
    public String checkOut(User user, HttpServletRequest request, ModelMap modelMap, CheckOut checkOut) {
        String uid = request.getParameter("uid");
        String howToPay = request.getParameter("howToPay");
        user = userService.SignIn(Integer.parseInt(uid));
        List<Cart> myCartProducts = cartService.getMyCartProducts(user.getUid());
        ArrayList<String> massage = new ArrayList<>();
        //----------------------------------------------查看各商品庫存是否足夠
        Iterator<Cart> iterator = myCartProducts.iterator();
        boolean flag = true;
        boolean check = true;
        while (iterator.hasNext()) {
            Cart cart = iterator.next();
            flag = checkOutService.checkQuantity(cart);
            if (!flag) {
                check = false;
                massage.add(cart.getSellProduct().getSellName() + "商品購買超過庫存了!");
                modelMap.addAttribute("massage", massage);
                continue;
            }
        }
        System.out.println("判斷完畢" + flag);
        if (!check) {
            return checkOutPage(user, request, modelMap);
        }
        //----------------------------------------------將訂單提取給各賣家
        System.out.println("將訂單提取給各賣家");
        Iterator<Cart> order = myCartProducts.iterator();
        while (order.hasNext()) {
            LocalDate localDate = LocalDate.now();
            System.out.println("時間 : " + localDate);
            Date date = Date.valueOf(localDate);
            Cart cart = order.next();
            SellProduct sellProduct = cart.getSellProduct();
            System.out.println("商品賣家 : " + sellProduct.getUser().getUsername());
            int buyQuantity = cart.getBuyQuantity();
            int sellQuantity = cart.getSellProduct().getSellQuantity();
            int res = sellQuantity - buyQuantity;
            sellProduct.setSellQuantity(res);
            sellProductService.alterMySellProduct(sellProduct);
            int productPrice = checkOutService.culPrice(sellProduct, buyQuantity);
            checkOut.setUser(user);
            checkOut.setAddress(user.getAddress());
            checkOut.setStatus("待出貨");
            checkOut.setHowToPay(howToPay);
            checkOut.setDate(date);
            checkOut.setBuyQuantity(cart.getBuyQuantity());
            checkOut.setProduct(sellProduct);
            checkOut.setTotalPrice(productPrice);
            checkOutService.save(checkOut);
        }
        //----------------------------------------------清空購物車
        cartService.dropAllMyCart(user.getUid());
        return checkOutPage(user, request, modelMap);
    }

}
