//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.domain.ResultInfo;
//import cn.itcast.travel.domain.User;
//import cn.itcast.travel.service.impl.UserServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.BeanUtil;
//import org.apache.commons.beanutils.BeanUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;
//
//@WebServlet("/loginServlet")
//public class LoginServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1 获取用户名和密码
//        Map<String, String[]> map = request.getParameterMap();
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//
//
//        // 2 封装user对象
//        User user = new User();
//        try {
//            BeanUtils.populate(user, map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        // 3 调用service完成查询
//        UserServiceImpl userService = new UserServiceImpl();
//        User u = userService.login(user);
////        System.out.println(u);
//        ResultInfo resultInfo = new ResultInfo();
//        //4 判断对象是否为空
//        if (u == null) {
//            //用户名密码或错误
//            resultInfo.setFlag(false);
//            resultInfo.setErrorMsg("用户名密码或错误,啦啦啦啦啦啦");
//        }
//        //判断用户是否激活
//        if(u != null && !"Y".equals(u.getStatus())){
//            //用户未激活
//            resultInfo.setFlag(false);
//            resultInfo.setErrorMsg("您尚未激活，请激活");
//        }
//        //判断登录成功
//        if(u !=null&&"Y".equals(u.getStatus())){
//            //用户登录成功
//            resultInfo.setFlag(true);
//            request.getSession().setAttribute("user",u);
//        }
//        //响应数据
//        ObjectMapper mapper = new ObjectMapper();
////        String s = mapper.writeValueAsString(resultInfo);
//        response.setContentType("application/json;charset=utf-8");
////        response.getWriter().write(s);
//        mapper.writeValue(response.getOutputStream(),resultInfo);
//
//    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doGet(request, response);
//    }
//}
