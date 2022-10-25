package com.cainiao.web.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*新建RequestDemo4 RequestDemo5 实现请求转发*/
@WebServlet("/RequestDemo4")
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo4..");

        //存储数据
        request.setAttribute("org","hello  world");

        //请求转发
        request.getRequestDispatcher("/RequestDemo5").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
