package com.ping.MyUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public void saveUser(User user) {
        System.out.println("-------------------------");
        System.out.println("準備儲存用戶資料");
        System.out.println("用戶名稱 : " + user.getUsername());
        System.out.println("用戶密碼 : " + user.getPassword());
        System.out.println("用戶地址 : " + user.getAddress());
        System.out.println("用戶電話 : " + user.getPhone());
        userDao.saveUser(user);
        System.out.println("用戶ID : " + user.getUid());
        System.out.println("儲存完畢");
    }

    public boolean checkUser(String username, String password) {
        System.out.println("-------------------------");
        System.out.println("確認是否有相同用戶");
        return userDao.checkUser(username, password);
    }


    public User SignIn(User user) {
        System.out.println("-------------------------");
        System.out.println("準備登入");
        User userList = userDao.SingIn(user);
        System.out.println("用戶ID : " + user.getUid());
        System.out.println("登入成功");
        return userList;
    }

    public User SignIn(int uid) {
        System.out.println("-------------------------");
        System.out.println("準備登入");
        System.out.println("用戶ID : " + uid);
        User user = userDao.SingIn(uid);
        System.out.println("登入成功");
        return user;
    }

    public boolean checkValues(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String address = user.getAddress();
        String phone = user.getPhone();
        String patten = "\\d{10}";
        Pattern p = Pattern.compile(patten);
        Matcher m = p.matcher(phone);
        if (username.equals("") || username.isEmpty() || username.length() > 10 || username.length() < 3 ) {
            System.out.println("名字格式錯誤");
            return false;
        } else if (!password.matches("^[A-Z][a-zA-Z0-9]{3,10}$")) {
            System.out.println("密碼格式錯誤");
            return false;
        } else if (address.equals("") || address.isEmpty() || address.length() > 30 ||address.length() < 15) {
            System.out.println("地址格式錯誤");
            return false;
        }else if(!m.matches()){
            System.out.println("手機格式錯誤");
            return false;
        }
        return true;
    }

    public boolean checkIDCard(String idCard) {
        System.out.println("進入方法");
        if(!idCard.matches("^[A-Za-z][12]\\d{8}$")){
            System.out.println("格式錯誤");
            return false;
        }
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String num[] = { "10", "11", "12", "13", "14", "15", "16", "17", "34", "18", "19", "20", "21", "22", "35", "23",
                "24", "25", "26", "27", "28", "29", "32", "30", "31", "33" };
        int i = s.indexOf(idCard.charAt(0)); // 抓出字串第一個的英文字母
        int x1 = Integer.parseInt(num[i]); // x1 英文對應的數字
        int res1 = ((x1 % 10) * 9) + (x1 / 10); // 第一個規則 英文轉成的數字, 個位數乘9再加上十位數
        int times = 1;
        for(int mult = 8 ; mult > 0 ; mult--) {
            int nums =  Character.getNumericValue(idCard.charAt(times));
            res1 +=  mult * nums;
            times++;
        }
//		System.out.println("檢查碼 : "+ (10-(res1 % 10)));
        if(Character.getNumericValue(idCard.charAt(9)) != 10-(res1 % 10)) {
            System.out.println("身分證不合法!");
            return false;
        }
        System.out.println("身分證合法!");
        return true;
    }
}
