package com.cainiao.dao.impl;

import com.cainiao.dao.DormitoryAdminDao;
import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.SystemAdmin;
import com.cainiao.util.JDBCUtils;

import javax.crypto.spec.PSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryAdminDaoImpl implements DormitoryAdminDao {
    @Override
    public List<DormitoryAdmin> list() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from dormitory.dormitory_admin";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集

        List<DormitoryAdmin> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String gender = resultSet.getString("gender");
                String telephone = resultSet.getString("telephone");
                DormitoryAdmin dormitoryAdminDao = new DormitoryAdmin(id, username, password, name, gender, telephone);
                list.add(dormitoryAdminDao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }
    @Override
    public List<DormitoryAdmin> search(String key, String value) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
//        String sql="select * from dormitory.dormitory_admin where ? like '%?%'";
//        statement.setString(1,key);
//        statement.setString(2,value);
        String sql = "select * from dormitory.dormitory_admin where " + key + " like '%" + value + "%'";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集

        List<DormitoryAdmin> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String gender = resultSet.getString("gender");
                String telephone = resultSet.getString("telephone");
                DormitoryAdmin dormitoryAdminDao = new DormitoryAdmin(id, username, password, name, gender, telephone);
                list.add(dormitoryAdminDao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }

    @Override
    public Integer save(DormitoryAdmin dormitoryAdmin) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into dormitory.dormitory_admin(username,password,name,gender,telephone) values(?,?,?,?,?) ";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryAdmin.getUsername());
            statement.setString(2, dormitoryAdmin.getPassword());
            statement.setString(3, dormitoryAdmin.getName());
            statement.setString(4, dormitoryAdmin.getGender());
            statement.setString(5, dormitoryAdmin.getTelephone());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer update(DormitoryAdmin dormitoryAdmin) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update dormitory.dormitory_admin set username=?,password=?,name=?,gender=?,telephone=? where id=?";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryAdmin.getUsername());
            statement.setString(2, dormitoryAdmin.getPassword());
            statement.setString(3, dormitoryAdmin.getName());
            statement.setString(4, dormitoryAdmin.getGender());
            statement.setString(5, dormitoryAdmin.getTelephone());
            statement.setInt(6,dormitoryAdmin.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "delete  from dormitory.dormitory_admin where id=?";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer register(String username, String password) throws SQLException {

        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from dormitory.dormitory_admin where username=?";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    public DormitoryAdmin login(String username) throws SQLException{
        Connection connection = JDBCUtils.getConnection();
        String sql="select * from dormitory.dormitory_admin where username ='"+username+"'";
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
                String gender = resultSet.getString(5);
                String telephone = resultSet.getString("telephone");
                return new DormitoryAdmin(id,username,password,name,gender,telephone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement);
        }
        return null;
    }
}
