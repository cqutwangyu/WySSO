package com.wy.sso.interceptors;

import com.wy.sso.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @ClassName RquestInterceptor
 * @Description 请求拦截器、跨域
 * @Author ChongqingWangYu
 * @DateTime 2019/5/30 21:16
 * @GitHub https://github.com/ChongqingWangYu
 */
@Component
public class RquestInterceptor extends HandlerInterceptorAdapter {
    private final static Logger logger = LoggerFactory.getLogger(RquestInterceptor.class);

    private final static class MethodType {
        /*axios请求时，会先发送一个OPTIONS请求*/
        private final static String OPTIONS = "OPTIONS";
    }

    /*除以下请求之外都需要验证Token才能通过*/
    private final static class UrlPath {
        private final static String getCodeImg = "/user/getCodeImg";
        private final static String login = "/user/login";
        private final static String register = "/user/register";
    }

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），
     * 不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 允许客户端携带跨域cookie
        // 当Access-Control-Allow-Credentials设为true的时候，Access-Control-Allow-Origin不能设为星号
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许指定域访问跨域资源
        //response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9006, http://127.0.0.1:8080");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));// *
        // 允许浏览器发送的请求消息头
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));// *
        // 允许浏览器在预检请求成功之后发送的实际请求方法名
        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
        // 设置响应数据格式
        response.setHeader("Content-Type", "application/json");
        if (request.getMethod().equals(MethodType.OPTIONS)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("X-Token");
        String requestURI = request.getRequestURI();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }
        //除login和register之外的请求需验证token
        if (requestURI.equals(UrlPath.getCodeImg) || requestURI.equals(UrlPath.login)
                || requestURI.equals(UrlPath.register) || TokenUtil.verify(token)) {
            long start = System.currentTimeMillis();
            request.setAttribute("start", start);
            logger.info(request.getRequestURI() + "请求到达");
            return true;
        }
        logger.info("请求：" + requestURI + "Token认证失败");
        return false;

    }
    /*下面的方法可以不重写*/

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）
     * 对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        if (!"OPTIONS".equals(request.getMethod())) {
            long start = (long) request.getAttribute("start");
            long end = System.currentTimeMillis();
            long spendTime = end - start;
            if (spendTime >= 1000) {
                logger.warn("方法耗时严重，请及时处理，耗时：" + spendTime);
            } else {
                logger.info("方法耗时正常，耗时：" + spendTime + "毫秒");
            }
        }
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 处理异步请求
     * 不是HandlerInterceptor的接口实现，是AsyncHandlerInterceptor的
     * AsyncHandlerInterceptor实现了HandlerInterceptor
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
