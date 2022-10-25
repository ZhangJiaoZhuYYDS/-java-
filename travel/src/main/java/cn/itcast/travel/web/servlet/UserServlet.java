package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")  // /user/add  /user/find
public class UserServlet extends BaseServlet {
    private  UserService userService = new UserServiceImpl();
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验比较
        String check = request.getParameter("check"); //获取表单提交的验证码
        System.out.println(check);
        //从session中获取验证码图片中的的验证码
        HttpSession session = request.getSession();
//        System.out.println(session.getAttribute("username"));

//        HttpSession session = request.getSession();

//        for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); )
//        {
//            System.err.println(e.nextElement()+
//                    "-----"+session.getAttribute((String) e.nextElement()));
//        }


        String checkcode_serve = (String) session.getAttribute("CHECKCODE_SERVER");

        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只能使用一次
        if (check==null || !checkcode_serve.equalsIgnoreCase(check)){
            System.out.println(false);
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //1 获取注册信息数据
        Map<String, String[]> map = request.getParameterMap();
        // 2 封装对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //System.out.println(user);
        // 3 调用service完成注册
//        UserService userService = new UserServiceImpl();
        boolean flag=userService.register(user);
        ResultInfo info=new ResultInfo();
        // 4 响应结果
        if (flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败,用户名已存在,点击刷新验证码重新操作");
        }
        //将info对象序列化json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取用户名和密码
        Map<String, String[]> map = request.getParameterMap();

        String username = request.getParameter("username");
        String password = request.getParameter("password");



        // 2 封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3 调用service完成查询
//        UserServiceImpl userService = new UserServiceImpl();
        User u = userService.login(user);
//        System.out.println(u);
        ResultInfo resultInfo = new ResultInfo();
        //4 判断对象是否为空
        if (u == null) {
            //用户名密码或错误
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名密码或错误,啦啦啦啦啦啦");
        }
        //判断用户是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            //用户未激活
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您尚未激活，请激活");
        }
        //判断登录成功
        if(u !=null&&"Y".equals(u.getStatus())){
            //用户登录成功
            resultInfo.setFlag(true);
            request.getSession().setAttribute("user",u);
        }
        //响应数据
        ObjectMapper mapper = new ObjectMapper();
//        String s = mapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(s);
        mapper.writeValue(response.getOutputStream(),resultInfo);
    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        System.out.println(user);
        // 将user写回客户端
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();//1 销毁session,实现退出功能
        //跳转页面
        response.sendRedirect(request.getContextPath()+"/login.html"); //request.getContextPath():虚拟目录；也即/travel
        System.out.println(request.getContextPath());
    }
}
