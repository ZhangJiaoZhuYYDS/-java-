package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service=new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 调用service完成查询
        List<Category> cs = service.findAll();
        // 2 序列化json返回
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(),cs);
        writeValue(cs,response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
