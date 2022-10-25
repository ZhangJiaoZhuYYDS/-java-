package com.cainiao.web.servlet;

import com.cainiao.pojo.Account;
import com.cainiao.service.BrandService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.weicoder.gson.GsonJson;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    private BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求的编码格式
        request.setCharacterEncoding("UTF-8");
        // 设置响应的编码格式
        response.setContentType("application/json; charset=utf-8");
        //处理Post请求的乱码问题
        request.setCharacterEncoding("utf-8");
        //获取请求参数  账号和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //判断方法
        String method = request.getParameter("method");
        //实例化session
        HttpSession session = request.getSession();
        //实例化GSON
        GsonJson gsonJson = new GsonJson();
        //分发方法
        switch (method){
            case "login":
                // 1 调用BrandService完成查询
                Account account = service.selectByUserNameAccount(username);
                if(account == null){
                    // 2 存入request域中
                request.setAttribute("result","不对啊，请检查账号密码");
                }else {
                    // 3 转发到brand.jsp

                session.setAttribute("account",account);
                    System.out.println(account);
                response.sendRedirect("erciyuandenglu/dist/index.jsp");
                    System.out.println(666);
                }

                break;
            case "logout":
                System.out.println(method);
                session.removeAttribute("account");
                response.sendRedirect("erciyuandenglu/index.jsp");
                break;
            case "register":
                //封装为account对象
                String name = request.getParameter("name");
                // 1 调用BrandService完成查询
                Account account3 = service.selectByUserNameAccount(username);
                if(account3==null){
                    Account account1 = new Account(username,Integer.valueOf(password),name);
                    //调用service方法
                    service.addAccount(account1);
                    //转发到查询所有Servlet
                    request.setAttribute("result","注册成功，请登录");
                request.getRequestDispatcher("/erciyuandenglu/register.jsp").forward(request,response);
                }
                else {
                    request.setAttribute("result","用户名或姓名存在，请修改");
                    System.out.println(6666);
                    request.getRequestDispatcher("/erciyuandenglu/register.jsp").forward(request,response);
                }
                break;
            case "select":
                String accountSelect = gsonJson.toJson(session.getAttribute("account")); //调用json方法将account转成json形式
                response.getWriter().write(accountSelect);
              break;
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
