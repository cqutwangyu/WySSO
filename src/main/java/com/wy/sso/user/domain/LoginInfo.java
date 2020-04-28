package com.wy.sso.user.domain;

import java.io.Serializable;

/**
 * @author wangyu
 * @title: LoginInfo
 * @projectName WySSO
 * @description: TODO
 * @date 2020/4/27 15:46
 */
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private String code;
    private String uuid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
