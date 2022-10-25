package com.cainiao.dao.impl;

import com.cainiao.dao.BuildingDao;
import com.cainiao.pojo.Building;
import com.cainiao.pojo.Dormitory;
import com.cainiao.pojo.SystemAdmin;
import com.cainiao.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDaoImpl implements BuildingDao {
    @Override
    public List<Building> list() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select b.id,b.name,b.introduction,d.name,d.id from dormitory.dormitory_admin d ,dormitory.building b where b.admin_id =d.id";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集

        List<Building> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String introduction = resultSet.getString(3);
                String adminName = resultSet.getString(4);
                Integer adminId = resultSet.getInt(5);
                list.add(new Building(id,name,introduction,adminId,adminName));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }

    @Override
    public List<Building> search(String key, String value) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select b.id,b.name,b.introduction,d.name,d.id from dormitory.dormitory_admin d ,dormitory.building b where b.admin_id =d.id and b."+key+" like '%"+value+"%' ";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集

        List<Building> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String introduction = resultSet.getString(3);
                String adminName = resultSet.getString(4);
                Integer adminId = resultSet.getInt(5);
                list.add(new Building(id,name,introduction,adminId,adminName));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }

    @Override
    public Integer save(String name, String introduction, Integer adminId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into dormitory.building(name,introduction,admin_id) values(?,?,?) ";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, introduction);
            statement.setInt(3, adminId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer update(Integer id, String name, String introduction, Integer adminId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update dormitory.building set name=?,introduction=?,admin_id=? where id= ?";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2, introduction);
            statement.setInt(3, adminId);
            statement.setInt(4, id);
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
        String sql = "delete  from dormitory.building  where id= ?";
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

}
