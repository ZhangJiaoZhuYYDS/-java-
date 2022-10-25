package com.cainiao.web.response;

/*response响应字符数据*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/rp3")
public class ResponseDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resp1...");

        //设置打印的类型为可识别的HTML格式并设置字符为utf-8 防止中文乱码
        response.setContentType("text/html;charset=utf-8");

        // 1 获取字符输入流
        PrintWriter writer = response.getWriter();

        writer.write("<h1>牛逼</h1>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
