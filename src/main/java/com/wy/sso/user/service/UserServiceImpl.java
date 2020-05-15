package com.wy.sso.user.service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wy.sso.framework.AbstractService;
import com.wy.sso.redis.RedisCache;
import com.wy.sso.user.domain.LoginInfo;
import com.wy.sso.user.domain.RoleInfo;
import com.wy.sso.user.domain.UserInfo;
import com.wy.sso.user.mapper.UserDao;
import com.wy.sso.utils.Constants;
import com.wy.sso.utils.TokenUtil;
import com.wy.sso.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wangyu
 * @title: UserServiceImpl
 * @projectName WyUpms
 * @description: TODO
 * @date 2020/4/20 18:47
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Object login(LoginInfo loginInfo) throws Exception {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + loginInfo.getUuid();
        String captcha = redisCache.getCacheObject(verifyKey);
        if (captcha == null) {
            throw new Exception("验证码已过期");
        }
        redisCache.deleteObject(verifyKey);
        if (!loginInfo.getCode().equalsIgnoreCase(captcha)) {
            throw new Exception("验证码错误");
        }
        UserInfo db_user = userDao.selectUserByName(loginInfo.getUserName());
        if (Objects.isNull(db_user)) {
            throw new Exception("用户名错误");
        }
        if (db_user.getPassword().equals(loginInfo.getPassword())) {
            String token = Constants.TOKEN_CODE_KEY + TokenUtil.sign(loginInfo.getUserName(), loginInfo.getPassword());
            db_user.setToken(token);
            List<RoleInfo> roles = userDao.selectUserRoles(db_user.getFlowId());
            StringBuilder roleNames = new StringBuilder();
            if (!roles.isEmpty()) {
                for (RoleInfo role : roles) {
                    roleNames.append(role.getRoleName()).append(",");
                }
                int length=roleNames.length();
                roleNames = roleNames.replace(length - 1, length, "");
                db_user.setRoleNames(roleNames.toString());
            }
            redisCache.setCacheObject(token, db_user, 30, TimeUnit.MINUTES);
            redisCache.setCacheObject(token + "roles", roles);
            return token;
        } else {
            throw new Exception("密码错误");
        }
    }

    @Override
    public Object register(UserInfo userInfo) throws Exception {
        userDao.insertUser(userInfo);
        return "注册成功";
    }

    @Override
    public Object findUserInfo(String userName) {
        return userDao.selectUserByName(userName);
    }

    @Override
    public Object findAllUserInfo() {
        return userDao.selectAllUser();
    }

    @Override
    public Object removeUserById(String userId) {
        return userDao.deleteUserById(userId) == 1 ? "删除成功" : "删除失败";
    }

    @Override
    public Object updateUserInfo(UserInfo userInfo) {
        int flag = userDao.updateUser(userInfo);
        if (flag == 1) {
            UserInfo newUserInfo = userDao.selectUserByName(currentUser().getUserName());
            newUserInfo.setToken(getToken());
            redisCache.setCacheObject(getToken(), newUserInfo, 30, TimeUnit.MINUTES);
        }
        return flag == 1 ? "更新成功" : "更新失败";
    }

    @Override
    public Object getCodeImg() throws Exception {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        try {
            redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        } catch (Exception e) {
            return "redis异常" + e;
        }
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        Map<String, String> result = new HashMap<>();
        result.put("uuid", uuid);
        result.put("img", Base64.encode(stream.toByteArray()));
        stream.close();
        return result;
    }
}
