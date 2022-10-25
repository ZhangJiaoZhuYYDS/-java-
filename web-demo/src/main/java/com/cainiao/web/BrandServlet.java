package com.cainiao.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("brand selectAll...");
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("brand add....");
    }

}
