package com.cainiao.web;

import com.cainiao.pojo.Dormitory;
import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.service.BuildingService;
import com.cainiao.service.DormitoryService;
import com.cainiao.service.StudentManagerService;
import com.cainiao.service.impl.BuildingServiceImpl;
import com.cainiao.service.impl.DormitoryServiceImpl;
import com.cainiao.service.impl.StudentManagerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/dormitoryServlet")
public class DormitoryServlet extends HttpServlet {
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private BuildingService buildingService = new BuildingServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method= request.getParameter("method");
        switch (method){
            case "list":
                try {
                    request.setAttribute("list",this.dormitoryService.list());
                    request.setAttribute("buildingList",this.buildingService.list());
                    request.getRequestDispatcher("dormitorymanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                String key = request.getParameter("key");
                String value=request.getParameter("value");
                try {
                    request.setAttribute("list",this.dormitoryService.search(key,value));
                    request.setAttribute("buildingList",this.buildingService.list());
                    request.getRequestDispatcher("dormitorymanager.jsp").forward(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "save":
                String buildingIdStr = request.getParameter("buildingId");
                Integer buildingId = Integer.parseInt(buildingIdStr);
                String name = request.getParameter("name");
                String typeStr = request.getParameter("type");
                Integer type =Integer.parseInt(typeStr);
                String telephone = request.getParameter("telephone");
                try {
                    this.dormitoryService.save(new Dormitory(buildingId,name,type,type,telephone));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("dormitoryServlet?method=list");
                break;
            case "update":
                String idStr=request.getParameter("id");
                Integer id=Integer.parseInt(idStr);
                name = request.getParameter("name");
                telephone = request.getParameter("telephone");
                try {
                    dormitoryService.update(new Dormitory(id,name,telephone));
                    response.sendRedirect("dormitoryServlet?method=list");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete" :
                idStr= request.getParameter("id");
                id= Integer.parseInt(idStr);
                try {
                    this.dormitoryService.delete(id);
                    response.sendRedirect("dormitoryServlet?method=list");
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
