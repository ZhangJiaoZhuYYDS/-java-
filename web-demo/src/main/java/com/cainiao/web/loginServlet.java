package com.cainiao.web;

import com.cainiao.pojo.Brand;
import com.cainiao.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        System.out.println(username);
        BrandService brandService = new BrandService();
        List<Brand> brands = brandService.selectAll();
        System.out.println(brands);
        System.out.println(username);
        // 2 存入request域中
        request.setAttribute("brands",brands);
        // 3 转发到brand.jsp
        request.getRequestDispatcher("/brand.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
