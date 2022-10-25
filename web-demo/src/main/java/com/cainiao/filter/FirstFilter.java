package com.cainiao.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/systemadmin.jsp")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getFilterName());
        System.out.println(filterConfig.getInitParameterNames());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中*
         * 无法得到的方法，就要把此request对象构造成HttpServletRequest
         */
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;  //强制类型转换 ，将filter强转为HTTPServlet（获取session的方法在它里面）
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        HttpSession session = httpRequest.getSession(); //获取session  里面的值
        Object systemAdmin = session.getAttribute("systemAdmin");
        Object Admin = session.getAttribute("dormitoryAdmin");
        Object type = session.getAttribute("type");
//        System.out.println(systemAdmin);
//        System.out.println(Admin);
//        System.out.println(type);
        if (systemAdmin==null&&Admin==null){
            System.out.println(666);
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp");
            return;
        }else {
            System.out.println(666666666);
            filterChain.doFilter(servletRequest,servletResponse);  //放行
        }
//        System.out.println(systemAdmin+"..."+Admin+"...."+type);
//        System.out.println("对request过滤");
//        filterChain.doFilter(servletRequest,servletResponse);
//        System.out.println("对response过滤");
    }

    @Override
    public void destroy() {

    }
}
