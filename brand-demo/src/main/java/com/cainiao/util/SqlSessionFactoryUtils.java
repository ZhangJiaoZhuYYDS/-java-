package com.cainiao.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        //静态代码块会随着类的加载而自动执行，且只执行一次
        try {
            // 3 调用mybatis完成查询
            // 3.1 获取sqlSessionFactory对象

            //获取核心配置文件中的输入流
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //获取SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {return sqlSessionFactory;}
}
