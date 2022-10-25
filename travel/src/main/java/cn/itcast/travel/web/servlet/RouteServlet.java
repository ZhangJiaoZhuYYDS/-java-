package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService=new RouteServiceImpl();
    /*分页查询*/
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1 接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        // 2 处理参数
        int cid = 0; //类别id
        if(cidStr!=null&&cidStr.length()>0){
            cid=Integer.parseInt(cidStr);
        }
        int currentPage = 0; //当前页码，如果不传递，则默认为第一页
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }
        int pageSize = 0; //每页显示条数，如果不传递，默认每页显示五条记录
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }
        // 3 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize);
        // 4 将pageBean序列化json,返回
        writeValue(pb,response);

    }


}
