package com.wy.sso.framework;

import com.wy.sso.framework.exception.UpmsException;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyu4017@sefonsoft.com
 * @title: AbstractController
 * @projectName SSM_CRM
 * @description: TODO
 * @date 2020/4/1 22:25
 */
public class AbstractController extends AbstractBase {
    private static final String STATUS_CODE = "code";
    private static final Integer SUCCEED_CODE = 200;
    private static final Integer FAILURE_CODE = 500;
    private static final String SUCCEED_MESSAGE = "succeed";
    private static final String MESSAGE_KEY = "message";
    private static final String DATA_KEY = "data";

    protected Map<String, Object> succeed(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS_CODE, SUCCEED_CODE);
        map.put(MESSAGE_KEY, SUCCEED_MESSAGE);
        map.put(DATA_KEY, data);
        log.info(SUCCEED_MESSAGE);
        return map;
    }

    protected Map<String, Object> failure(String message) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.hasText(message)) {
            throw new IllegalArgumentException("message should not be empty");
        }
        map.put(STATUS_CODE, FAILURE_CODE);
        map.put(MESSAGE_KEY, message);
        log.error(message);
        return map;
    }

    protected Map<String, Object> failure(Exception e) {
        Map<String, Object> map = new HashMap<>();
        String message = e.getMessage();
        if (!StringUtils.hasText(message)) {
            throw new IllegalArgumentException("message should not be empty");
        }
        Integer statusCode = UpmsException.getStatusCode(message);
        if (statusCode != 0) {
            map.put(STATUS_CODE, statusCode);
        } else {
            map.put(STATUS_CODE, FAILURE_CODE);
        }
        map.put(MESSAGE_KEY, message);
        log.error(message);
        return map;
    }

}
