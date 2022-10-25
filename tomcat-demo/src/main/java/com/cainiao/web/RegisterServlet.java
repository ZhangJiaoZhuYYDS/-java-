package com.cainiao.web;

import com.cainiao.mapper.UserMapper;
import com.cainiao.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 1 接收用户数据
        String username = request.getParameter("username");
        String password=request.getParameter("password");

    // 2 封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

    // 3 调用mybatis完成查询
        // 3.1 获取sqlSessionFactory对象
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 3.2 获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.3 获取mapper
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);

        // 3.4 调用方法
        User u = usermapper.selectByUserName(username);


        // 4 判断用户对象是否存在，若存在则为null
        if (u == null) {
            // 用户名不存在，添加用户
            usermapper.add(user);
            //提交事务（默认事务为手动提交）
            sqlSession.commit();
            sqlSession.close();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户注册成功");
        }else {
            //用户名存在,给出提示信息
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户名已存在");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
