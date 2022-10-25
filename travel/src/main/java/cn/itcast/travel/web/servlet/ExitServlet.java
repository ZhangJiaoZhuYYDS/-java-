//package cn.itcast.travel.web.servlet;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet("/exitServlet")
//public class ExitServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getSession().invalidate();//1 销毁session,实现退出功能
//        //跳转页面
//        response.sendRedirect(request.getContextPath()+"/login.html"); //request.getContextPath():虚拟目录；也即/travel
//        System.out.println(request.getContextPath());
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doGet(request, response);
//    }
//}
