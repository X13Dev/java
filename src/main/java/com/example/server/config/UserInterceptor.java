package com.example.server.config;

import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    // Controller方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 同样在这里调用用户服务传入session，判断用户是否登录或者有效
        // 未登录则重定向至主页（暂定主页就是/）
        if (!userService.isLogin(request.getSession()).isSuccess()) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    // Controller方法执行之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 整个请求完成后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}