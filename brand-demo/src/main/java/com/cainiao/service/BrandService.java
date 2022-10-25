package com.cainiao.service;

import com.cainiao.mapper.BrandMapper;
import com.cainiao.pojo.Account;
import com.cainiao.pojo.Brand;
import com.cainiao.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class BrandService {
    public static void main(String[] args) {
        System.out.println(new BrandService().selectAll());
    }

//    brand 查询
    //获取sqlsessionfactory对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    /*查询所有*/
    //调用BrandMapper.selectAll()
    public List<Brand> selectAll(){
              //从util中使用类名调用静态方法获取sqlSessionFactory对象

        // 获取SqlSession 是mybatis提供的操作数据库的对象
        // SqlSession sqlSession = factory.openSession(true); //添加true表示事务自动提交
        SqlSession sqlSession = factory.openSession(true);
        // 1 获取BrandMapper的代理实现类对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //2  调用mapper接口中的方法
        List<Brand> brands = mapper.selectAll();
//        //同 1 2   提供sql以及唯一标识找到sql并执行，唯一标识是namespace.sqlId
//        List list=sqlSession.select("com.cainiao.mapper.BrandMapper.selectAl");
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


// account 查询---------------------------------------------------------------------

    /*查询所有account*/
    //调用BrandMapper.selectAll()
    public List<Account> selectAllAccount(){
    //从util中使用类名调用静态方法获取sqlSessionFactory对象
    // 获取SqlSession
    SqlSession sqlSession = factory.openSession();
    // 获取BrandMapper
    BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
    // 调用方法
    List<Account> accounts = mapper.selectAllAccount();
    sqlSession.close();
    return accounts;
}

    /*根据username查询*/
    public Account selectByUserNameAccount(String username){
        //获取Sqlsession
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        Account account = mapper.selectByUserNameAccount(username);
        //释放资源
        sqlSession.close();
        return account;
    }

    /*添加*/
    public void addAccount(Account account){

        //获取Sqlsession
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.addAccount(account);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }



}
