package com.wy.sso.utils;

/**
 * @author wangyu
 * @title: Constants
 * @projectName WySSO
 * @description: 常量
 * @date 2020/4/27 15:39
 */
public class Constants {

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码 token key
     */
    public static final String TOKEN_CODE_KEY = "token_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
}
