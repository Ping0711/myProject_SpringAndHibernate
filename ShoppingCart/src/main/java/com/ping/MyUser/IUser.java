package com.ping.MyUser;

import java.util.List;

public interface IUser {
    public void saveUser(User user);

    public boolean checkUser(String username, String password);

    public User SingIn(User user);

    public User SingIn(int uid);
}
