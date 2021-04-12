package com.hopu.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断用户是否登录
        Object login_user = request.getSession().getAttribute("loginUser");
        if(login_user==null){
            // 拦截到登录页面
            response.sendRedirect(request.getContextPath()+"/user/toLoginPage");
            return false;
        }
        // 已登录，放行
        return true;
    }
}
