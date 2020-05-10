package com.wy.sso.user;

import com.wy.sso.framework.AbstractController;
import com.wy.sso.user.domain.LoginInfo;
import com.wy.sso.user.domain.UserInfo;
import com.wy.sso.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyus
 * @title: UserContoller
 * @projectName WyUpms
 * @description: TODO
 * @date 2020/4/20 18:22
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @return token
     */
    @PostMapping("/login")
    @ResponseBody
    public Object login(LoginInfo loginInfo) {
        try {
            return succeed(userService.login(loginInfo));
        } catch (Exception e) {
            return failure(e);
        }
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getInfo")
    @ResponseBody
    public Object getInfo() {
        try {
            return succeed(userService.findUserInfo(getUserName()));
        } catch (Exception e) {
            return failure(e);
        }
    }

    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Object register(UserInfo userInfo) {
        try {
            return succeed(userService.register(userInfo));
        } catch (Exception e) {
            return failure(e);
        }
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping("/getAll")
    @ResponseBody
    public Object getAllUser() {
        try {
            return succeed(userService.findAllUserInfo());
        } catch (Exception e) {
            return failure(e);
        }
    }
    /**
     * 获取验证码
     *
     * @return
     */
    @GetMapping("/getCodeImg")
    @ResponseBody
    public Object getCodeImg() {
        try {
            return succeed(userService.getCodeImg());
        } catch (Exception e) {
            return failure(e);
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/remove")
    @ResponseBody
    public Object deleteUser(String userId) {
        try {
            return succeed(userService.removeUserById(userId));
        } catch (Exception e) {
            return failure(e);
        }
    }

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public Object updateUser(UserInfo userInfo) {
        try {
            return succeed(userService.updateUserInfo(userInfo));
        } catch (Exception e) {
            return failure(e);
        }
    }
}
