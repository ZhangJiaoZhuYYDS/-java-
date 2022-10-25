package com.cainiao.web;

import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.StudentManager;
import com.cainiao.service.DormitoryService;
import com.cainiao.service.StudentManagerService;
import com.cainiao.service.impl.DormitoryServiceImpl;
import com.cainiao.service.impl.StudentManagerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/studentManagerServlet")
public class StudentManagerServlet extends HttpServlet {
    private StudentManagerService studentManagerService = new StudentManagerServiceImpl();
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        switch (method){
            case "list":
                try {
                    request.setAttribute("list",this.studentManagerService.list());
                    request.setAttribute("dormitoryList",this.dormitoryService.availableList());
                    request.getRequestDispatcher("studentmanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                String key = request.getParameter("key");
                String value=request.getParameter("value");
                try {
                    request.setAttribute("list",this.studentManagerService.search(key,value));
                    request.getRequestDispatcher("studentmanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case "save":
                String dormitory = request.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitory);
                String number = request.getParameter("number");
                String name = request.getParameter("name");
                String gender = request.getParameter("gender");
                try {
                    this.studentManagerService.save(new StudentManager(number,name,gender,dormitoryId));
                    response.sendRedirect("studentManagerServlet?method=list");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "update":
                String dormitoryIdStr = request.getParameter("dormitoryId");
                dormitoryId = Integer.parseInt(dormitoryIdStr);
                String idStr = request.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                number= request.getParameter("number");
                name=request.getParameter("name");
                gender= request.getParameter("gender");
                String oldDormitoryIdStr=request.getParameter("oldDormitoryId");
                Integer oldDormitoryId = Integer.parseInt(oldDormitoryIdStr);
                try {
                    this.studentManagerService.update(new StudentManager(id,number,name,gender,dormitoryId),oldDormitoryId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("studentManagerServlet?method=list");
                break;
            case "delete":
                idStr=request.getParameter("id");
                id=Integer.parseInt(idStr);
                dormitoryIdStr = request.getParameter("dormitoryId");
                dormitoryId=Integer.parseInt(dormitoryIdStr);
                try {
                    this.studentManagerService.delete(id,dormitoryId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("studentManagerServlet?method=list");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
