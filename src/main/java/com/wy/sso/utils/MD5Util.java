package com.wy.sso.utils;

import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName MD5Util
 * @Description MD5加密工具
 * @Author ChongqingWangYu
 * @DateTime 2019/6/14 18:18
 * @GitHub https://github.com/ChongqingWangYu
 */
public class MD5Util {

    public static String encode(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    public static String encodeFile(MultipartFile file) throws IOException {
        return DigestUtils.md5DigestAsHex(file.getBytes());
    }
}
