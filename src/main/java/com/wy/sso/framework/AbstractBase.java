package com.wy.sso.framework;

import com.wy.sso.user.domain.UserInfo;
import com.wy.sso.user.mapper.UserDao;
import com.wy.sso.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangyu4017@sefonsoft.com
 * @title: AbstractController
 * @projectName SSM_CRM
 * @description: TODO
 * @date 2020/4/1 21:42
 */
public class AbstractBase {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    protected HttpServletRequest request;

    protected UserInfo currentUser() {
        return getUser(getUserName());
    }

    protected String getToken() {
        return request.getHeader("X-Token");
    }

    protected UserInfo getUser(String userName) {
        return userDao.selectUserByName(userName);
    }

    protected String getUserName() {
        return TokenUtil.getUserName(getToken());
    }
}
