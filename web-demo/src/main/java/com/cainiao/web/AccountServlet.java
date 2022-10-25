package com.cainiao.web;

import com.cainiao.dto.DormitoryAdminDto;
import com.cainiao.dto.SystemAdminDto;
import com.cainiao.service.DormitoryAdminService;
import com.cainiao.service.impl.DormitoryAdminServiceImpl;
import com.cainiao.service.impl.SystemAdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    SystemAdminServiceImpl systemAdminService = new SystemAdminServiceImpl();
    DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");  //设置编码格式，防止中文乱码
        String type = request.getParameter("type"); //获取登录账户类型
        System.out.println(type);
//        String registerType = request.getParameter("registerType");//获取注册账户类型
//        System.out.println(registerType);
        String method =request.getParameter("method");
        String username = request.getParameter("username"); //获取登录界面账号
        String password = request.getParameter("password"); //获取登录界面密码

        String dormitoryUsername = request.getParameter("dormitoryUsername"); //获取注册界面账号
        String dormitoryPassword = request.getParameter("dormitoryPassword"); //获取注册界面密码
        String dormitoryName = request.getParameter("dormitoryName"); //获取注册界面姓名
        String dormitoryGender = request.getParameter("dormitoryGender"); //获取注册界面性别
        System.out.println(dormitoryGender);
        String dormitoryTelephone = request.getParameter("dormitoryTelephone"); //获取注册界面电话
        /*对登录用户类型进行判断*/
        if(type.equals("systemAdmin")){  //管理员类型管理员
            switch(method){
                case "logout":
                    request.getSession().invalidate();  //销毁session,跳转页面
                    response.sendRedirect("login.jsp");
                    break;
                case "login":
                    try {
                        SystemAdminDto systemAdminDto = systemAdminService.login(username, password);
                        //判断用户名是否存在，进行信息反馈
                        switch(systemAdminDto.getCode()){
                            case -1:
                                request.setAttribute("usernameError","用户名不存在");
                                request.getRequestDispatcher("login.jsp").forward(request,response);
                                break;
                            case 0:
                                request.setAttribute("passwordError","密码错误");
                                request.getRequestDispatcher("login.jsp").forward(request,response);
                                break;
                            case 1:
                                System.out.println("登录成功");
                                HttpSession session = request.getSession();
                                session.setAttribute("systemAdmin",systemAdminDto.getSystemAdmin());
                                session.setAttribute("type",type);
                                response.sendRedirect("systemadmin.jsp");
                                break;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }else{                       // 楼管类型管理员
            switch(method){
                case "dormitoryCheckUsername": //用ajax异步核对是否存在重复账号
                    try {
                        DormitoryAdminDto dormitoryAdminDto = systemAdminService.dormitoryCheckUsername(dormitoryUsername);
                        //判断用户名是否存在，进行信息反馈
                        switch(dormitoryAdminDto.getCode()){
//                       case -1:
//                         request.setAttribute("usernameError","用户名存在");
//                         response.getWriter().write("-1");
////                         request.getRequestDispatcher("login.jsp").forward(request,response);
//                         break;
                            case 0:
                                response.getWriter().write("0");
//                           request.getRequestDispatcher("login.jsp").forward(request,response);
                                break;
                            case 1:
                                response.getWriter().write("1");
                                break;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "dormitorySave": //把注册好的账号进行保存
                    try {
                        Integer integer = systemAdminService.dormitorySave(dormitoryUsername, dormitoryPassword, dormitoryName, dormitoryGender, dormitoryTelephone);
                        response.getWriter().write("注册成功，请登录");
                        response.sendRedirect("login.jsp");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "login": //楼管登录
                    try {
                        DormitoryAdminDto login = dormitoryAdminService.login(username, password);
                        //判断用户名是否存在，进行信息反馈
                        switch(login.getCode()){
                            case -1:
                                request.setAttribute("usernameError","用户名不存在");
                                request.getRequestDispatcher("login.jsp").forward(request,response);
                                break;
                            case 0:
                                request.setAttribute("passwordError","密码错误");
                                request.getRequestDispatcher("login.jsp").forward(request,response);
                                break;
                            case 1:
                                HttpSession session = request.getSession();
                                session.setAttribute("dormitoryAdmin",login.getDormitoryAdmin());
                                session.setAttribute("type","dormitoryAdmin");
                                response.sendRedirect("systemadmin.jsp");
                                break;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "logout":
                    request.getSession().invalidate();  //销毁session,跳转页面
                    response.sendRedirect("login.jsp");
                    break;

            }
        }

//        System.out.println(request.getContextPath());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
     }
}
