package com.cainiao.web;

import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.service.DormitoryAdminService;
import com.cainiao.service.impl.DormitoryAdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/dormitoryAdminServlet")
public class DormitoryAdminServlet extends HttpServlet {
    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        switch (method){
            case "list":
                try {
                    request.setAttribute("list",this.dormitoryAdminService.list());
                    request.getRequestDispatcher("adminmanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                String key = request.getParameter("key");
                String value=request.getParameter("value");
                try {
                    request.setAttribute("list",this.dormitoryAdminService.search(key,value));
                    request.getRequestDispatcher("adminmanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case "save":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String gender = request.getParameter("gender");
                String telephone = request.getParameter("telephone");
                try {
                    this.dormitoryAdminService.save(new DormitoryAdmin(username,password,name,gender,telephone));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("dormitoryAdminServlet?method=list");
                break;
            case "update":
                String idStr=request.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                username = request.getParameter("username");
                password = request.getParameter("password");
                name = request.getParameter("name");
                gender = request.getParameter("gender");
                telephone = request.getParameter("telephone");
                try {
                    this.dormitoryAdminService.update(new DormitoryAdmin(id,username,password,name,gender,telephone));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("dormitoryAdminServlet?method=list");
                break;
            case "delete":
                idStr=request.getParameter("id");
                id=Integer.parseInt(idStr);
                try {
                    this.dormitoryAdminService.delete(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("dormitoryAdminServlet?method=list");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
