package com.cainiao.web;

import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.service.BuildingService;
import com.cainiao.service.DormitoryAdminService;
import com.cainiao.service.impl.BuildingServiceImpl;
import com.cainiao.service.impl.DormitoryAdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/buildingServlet")
public class BuildingServlet extends HttpServlet {
    private BuildingService buildingService =new BuildingServiceImpl();
    private DormitoryAdminService dormitoryAdminService= new DormitoryAdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Utf-8");
        String method=request.getParameter("method");
        switch (method){
            case "list" :
                try {
                    request.setAttribute("list",this.buildingService.list());
                    request.setAttribute("dormitoryAdminLists",this.dormitoryAdminService.list());
                    request.getRequestDispatcher("buildingmanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                String key = request.getParameter("key");
                String value=request.getParameter("value");
                try {
                    request.setAttribute("list",this.buildingService.search(key,value));
                    request.setAttribute("dormitoryAdminLists",this.dormitoryAdminService.list());
                    request.getRequestDispatcher("buildingmanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "save":
                String name = request.getParameter("name");
                String introduction = request.getParameter("introduction");
                String adminIdStr=request.getParameter("adminId");
                Integer adminId = Integer.parseInt(adminIdStr);
                try {
                    this.buildingService.save(name,introduction,adminId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("dormitoryAdminServlet?method=list");
                break;
            case "update":
                String idStr=request.getParameter("id");
                Integer id=Integer.parseInt(idStr);
                name = request.getParameter("name");
                introduction = request.getParameter("introduction");
                adminIdStr =request.getParameter("adminId");
                adminId = Integer.parseInt(adminIdStr);
                try {
                    buildingService.update(id,name,introduction,adminId);
                    response.sendRedirect("buildingServlet?method=list");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete" :
                idStr= request.getParameter("id");
                id= Integer.parseInt(idStr);
                try {
                    this.buildingService.delete(id);
                    response.sendRedirect("buildingServlet?method=list");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
