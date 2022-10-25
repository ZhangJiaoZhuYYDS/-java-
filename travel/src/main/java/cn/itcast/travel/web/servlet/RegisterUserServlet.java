//package cn.itcast.travel.web.servlet;
//
//import cn.itcast.travel.domain.ResultInfo;
//import cn.itcast.travel.domain.User;
//import cn.itcast.travel.service.UserService;
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
//import java.util.Enumeration;
//import java.util.Map;
//
//@WebServlet("/RegisterUserServlet")
//public class RegisterUserServlet extends HttpServlet {
//    private HttpSession session;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doGet(request, response);
//        //验证码校验比较
//        String check = request.getParameter("check"); //获取表单提交的验证码
//        System.out.println(check);
//        //从session中获取验证码图片中的的验证码
//        HttpSession session = request.getSession();
////        System.out.println(session.getAttribute("username"));
//
////        HttpSession session = request.getSession();
//
////        for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); )
////        {
////            System.err.println(e.nextElement()+
////                    "-----"+session.getAttribute((String) e.nextElement()));
////        }
//
//
//        String checkcode_serve = (String) session.getAttribute("CHECKCODE_SERVER");
//
//        session.removeAttribute("CHECKCODE_SERVER");//保证验证码只能使用一次
//        if (check==null || !checkcode_serve.equalsIgnoreCase(check)){
//            System.out.println(false);
//            //验证码错误
//            ResultInfo info = new ResultInfo();
//            //注册失败
//            info.setFlag(false);
//            info.setErrorMsg("验证码错误");
//            //将info对象序列化
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(info);
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(json);
//            return;
//        }
//
//        //1 获取注册信息数据
//        Map<String, String[]> map = request.getParameterMap();
//        // 2 封装对象
//        User user=new User();
//        try {
//            BeanUtils.populate(user,map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        //System.out.println(user);
//        // 3 调用service完成注册
//        UserService userService = new UserServiceImpl();
//        boolean flag=userService.register(user);
//        ResultInfo info=new ResultInfo();
//        // 4 响应结果
//        if (flag){
//            //注册成功
//            info.setFlag(true);
//        }else {
//            //注册失败
//            info.setFlag(false);
//            info.setErrorMsg("注册失败,用户名已存在,点击刷新验证码重新操作");
//        }
//        //将info对象序列化json
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
//
//        //将json数据写回客户端
//        //设置content-type
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(json);
//
//    }
//}
