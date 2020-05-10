package com.wy.sso.framework;

import com.wy.sso.user.domain.RoleInfo;
import com.wy.sso.user.domain.UserInfo;
import com.wy.sso.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private RedisCache redisCache;

    @Autowired
    protected HttpServletRequest request;

    protected boolean isAdmin() {
        for (RoleInfo roleInfo : getRoles()) {
            if (roleInfo.getRoleName().equals("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    protected List<RoleInfo> getRoles() {
        return redisCache.getCacheObject(getToken() + "roles");
    }

    protected UserInfo currentUser() {
        return redisCache.getCacheObject(getToken());
    }

    protected String getToken() {
        return request.getHeader("X-Token");
    }


    protected String getUserName() {
        return currentUser().getUserName();
    }
}
