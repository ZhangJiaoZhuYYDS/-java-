package com.cainiao.web.request;


/*Request获取请求数据--请求行&请求头&请求体*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/rq1")
public class RequestDemo1  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*我们在搜索框输入http://localhost:8080/tomcat-demo/rq1?username=zhangsan&password=123*/

        /*获取请求方式：GET*/
        String method = req.getMethod();
        System.out.println(method);
        /*获取虚拟目录（项目访问路径） 默认时 模块名称:tomcat-demo*/
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        /*获取URL(统一资源定位符)          http://localhost:8080/tomact-demo/rq1*/
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL.toString());
        /*获取URI(统一资源标识符)            /tomcat-demo/rq1*/
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        /*获取请求参数（GET方式）             username=zhangsan&password=123*/
        String queryString = req.getQueryString();
        System.out.println(queryString);


        /*获取请求头： user-agent：浏览器的版本信息 */
        String agent = req.getHeader("user-agent");
        System.out.println(agent);
        /*Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.66 Safari/537.36 Edg/103.0.1264.44*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取post  请求体:请求参数 */
        // 1 获取字符输入流
        BufferedReader br = req.getReader();

        // 2 读取数据
        String s = br.readLine();
        System.out.println(s);  /*username=DAshen666554&password=123456*/
    }
}
