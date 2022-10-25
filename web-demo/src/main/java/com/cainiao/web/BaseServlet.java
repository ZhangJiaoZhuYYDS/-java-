package com.cainiao.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*替换servlet,根据请求行最后一段进行方法分发  HttpServlet里面有个service方法，用于判断请求方式，进而执行doGet()和doPost()方法*/


public class BaseServlet extends HttpServlet {
    //根据请求行的最后一段路径进行分发
    @Override
    //重写service方法
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求路径
        String uri = req.getRequestURI(); //brand-demo/brand/selectAll
        //2 获取最后一段方法名
        int index=uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);
        System.out.println(methodName);//selectAll

        //3 执行方法
        //3.1 获取BrandServlet UserServlet 字节码对象 Class
        //谁调用我（this 所在方法）， 我（this）代表谁
        System.out.println(this); //com.cainiao.web.servlet.BrandServlet@563ee426
        Class<? extends BaseServlet> cls = this.getClass();
        //3.2 获取Method方法对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //3.3 执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
