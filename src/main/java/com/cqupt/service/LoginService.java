package com.cqupt.service;

import com.cqupt.dao.UserDao;

public class LoginService {
    public boolean checkAccount(String userName,String password){
        UserDao userDao = new UserDao();
        if (password==null||password.trim()==""||userName==null||userName.trim()==""){
            return false;
        }
        String realPassword = userDao.getPasswordByUserMame(userName);
        if(realPassword!=null&&realPassword.equals(password)){
            return true;
        }else {
            return false;
        }
    }
}
