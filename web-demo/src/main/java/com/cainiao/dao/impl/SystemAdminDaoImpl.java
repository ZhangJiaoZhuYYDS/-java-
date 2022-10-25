package com.cainiao.dao.impl;

import com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl;
import com.cainiao.dao.SystemAdminDao;

import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.SystemAdmin;
import com.cainiao.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SystemAdminDaoImpl implements SystemAdminDao {

    @Override
    public SystemAdmin findByUsername(String username) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql="select * from system_admin where username ='"+username+"'";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                username =resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String telephone = resultSet.getString("telephone");
                SystemAdmin systemAdmin = new SystemAdmin(id,username,password,name,telephone);
                return systemAdmin;
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement);
        }
        return null;
        }

    @Override
    public DormitoryAdmin DormitoryFindByUsername(String domitoryUsername) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql="select * from dormitory.dormitory_admin where username ='"+domitoryUsername+"'";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                domitoryUsername =resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String telephone = resultSet.getString("telephone");
                return new DormitoryAdmin(id,domitoryUsername,password,name,gender,telephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement);
        }
        return null;
    }

    @Override
    public Integer dormitorySave(String dormitoryUsername, String dormitoryPassword, String dormitoryName, String dormitoryGender, String dormitoryTelephone) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into dormitory.dormitory_admin(username,password,name,gender,telephone) values(?,?,?,?,?) ";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryUsername);
            statement.setString(2, dormitoryPassword);
            statement.setString(3, dormitoryName);
            statement.setString(4, dormitoryGender);
            statement.setString(5,dormitoryTelephone);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }
}

