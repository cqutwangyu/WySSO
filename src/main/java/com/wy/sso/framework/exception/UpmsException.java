package com.wy.sso.framework.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyu4017@sefonsoft.com
 * @title: CRMException
 * @projectName SSM_CRM
 * @description: 异常信息类
 * @date 2020/3/31 18:00
 */
public class UpmsException {
    private static final Map<String, Integer> map = new HashMap<>();
    public static final String INPUT_USER_NAME_ALREADY_EXISTS = "您输入的用户名已存在，请重新输入！";
    public static final String INPUT_CUSTOMER_NAME_ALREADY_EXISTS = "您输入的客户名称已存在，请重新输入！";

    static {
        map.put(INPUT_USER_NAME_ALREADY_EXISTS, 1001);
        map.put(INPUT_CUSTOMER_NAME_ALREADY_EXISTS, 1002);
    }

    public static Integer getStatusCode(String key) {
        return map.containsKey(key) ? map.get(key) : 777;
    }
}
