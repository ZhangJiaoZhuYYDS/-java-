package com.cainiao.web.request;


/*Request通用方式获取请求参数*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/rq2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get请求逻辑
        System.out.println("get.....");
        // 1 获取所有参数的Map集合
       // System.out.println(req.getParameterMap());  //{username=[Ljava.lang.String;@69533d30, password=[Ljava.lang.String;@3baeda81, hobby=[Ljava.lang.String;@6ca07235}
        Map<String, String[]> Map = req.getParameterMap();
        //System.out.println(Map);    //{username=[Ljava.lang.String;@69533d30, password=[Ljava.lang.String;@3baeda81, hobby=[Ljava.lang.String;@6ca07235}
        //System.out.println(Map.keySet());    //[username, password, hobby]
        for (String key : Map.keySet()){
            System.out.println(key+":");
            //获取值
            String[] values = Map.get(key);
            for (String value: values){
                System.out.println(value + " ");
            }
            System.out.println( );
        }
        System.out.println("....................");

        // 2 根据key获取参数值，数组

        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies){
            System.out.println(hobby);
        }

        // 3 根据key获取单个参数值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);

    }

    //post请求  方法与get请求一样 直接调用
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
