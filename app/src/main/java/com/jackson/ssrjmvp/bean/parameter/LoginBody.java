package com.jackson.ssrjmvp.bean.parameter; /**
 * LoginBody  2017-10-30
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

/**
 * class description here
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 10 30
 */
public class LoginBody {
    private String passWord;
    private String userName;

    public LoginBody(String passWord, String userName) {
        this.passWord = passWord;
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

