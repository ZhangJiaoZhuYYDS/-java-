package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    //重写service方法
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("service值");

        // 完成方法分发
        // 1 获取请求路径
        String requestURI = request.getRequestURI();  // /travel/user/add
        // 2 获取方法名称
        String methodName = requestURI.substring(requestURI.lastIndexOf('/') + 1);
//        System.out.println(methodName); //add
        // 3 获取方法对象method
        // 谁调用我，我代表谁    UserServlet
//        System.out.println(this); //cn.itcast.travel.web.servlet.UserServlet@4c74d519
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        //报错   第一种 可以实行暴力破解，因为方法权限公开性  忽略访问权限修饰符  Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        // 暴力反射  method.setAccessible(true);
        //       第二种 把修饰符protected改为public
            // 4 执行方法
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    /*直接将传入的对象序列化为json,并且写回客户端*/
    public void writeValue(Object object,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),object);
    }
    /*直接将传入的对象序列化为json,返回给调用者*/
    public String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


}
