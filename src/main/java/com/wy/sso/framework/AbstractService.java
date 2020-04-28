package com.wy.sso.framework;

/**
 * @author wangyu4017@sefonsoft.com
 * @title: AbstractService
 * @projectName SSM_CRM
 * @description: TODO
 * @date 2020/4/1 22:23
 */
public class AbstractService extends AbstractBase {

    protected static String result(int i) {
        return i == 1 ? "操作成功" : "操作失败";
    }
}
