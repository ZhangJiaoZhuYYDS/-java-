package com.cainiao.web.request;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/*方法介绍*/
/*urlPatterns也可写成value loadOnStartup默认为0 就是servlet被第一次访问时会被调用，如果数值大于0，则会提前servlet被第一次调用时，被调用*/
@WebServlet(urlPatterns = "/demo2" ,loadOnStartup = 1)
public class ServletDemo2 implements Servlet {
    private ServletConfig config;

    /*init() 初始化方法
    *1 调用时机 ：默认情况下。servlet被第一次访问时，调用
    *2 调用次数 1次 */
    public void init(ServletConfig config) throws ServletException {
//        this.config=config;
        System.out.println("init");
//        System.out.println(getServletConfig());
    }

    /*service（） 提供服务方法
    * 1 调用时机 ： 每一次Servlet被调用时，调用
    * 2 调用次数 ： 多次*/
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        System.out.println(servletRequest);
//        System.out.println(servletResponse);
        System.out.println("servlet ");
    }

    /*destroy() 销毁方法
    * 1 调用时机：内存释放或者服务器关闭时，servlet对象被销毁，调用
    * 2 调用次数 1次*/
    public void destroy() {}

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
//        return config;
    }
}
