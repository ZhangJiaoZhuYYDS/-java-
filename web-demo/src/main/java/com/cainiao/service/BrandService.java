package com.cainiao.service;

import com.cainiao.mapper.BrandMapper;
import com.cainiao.pojo.Brand;
import com.cainiao.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    /*查询所有*/
    //调用BrandMapper.selectAll()
    public List<Brand> selectAll(){
              //从util中使用类名调用静态方法获取sqlSessionFactory对象

        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        // 调用方法
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    /*添加*/
    public void add(Brand brand){

        //获取Sqlsession
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.add(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();


    }

    /*根据Id查询*/
    public Brand selectById(int id){
        //获取Sqlsession
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        Brand brand = mapper.selectById(id);
        //释放资源
        sqlSession.close();
        return brand;
    }


    /*添加功能*/
    public void update(Brand brand){
        //获取Sqlsession
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.update(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    };


}
