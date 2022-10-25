package com.cainiao.dao.impl;

import com.cainiao.dao.DormitoryDao;
import com.cainiao.pojo.Dormitory;
import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryDaoImpl implements DormitoryDao {
    @Override
    public List<Dormitory> list() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select d.id,b.name,d.name,d.type,d.available,d.telephone from dormitory.dormitory d ,dormitory.building b where d.building_id=b.id";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集
        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String name = resultSet.getString(3);
                Integer type = resultSet.getInt(4);
                Integer available = resultSet.getInt(5);
                String telephone =resultSet.getString(6);
                list.add(new Dormitory(id,buildingName,name,type,available,telephone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }
    @Override
    public List<Dormitory> search(String key, String value) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
//        String sql="select * from dormitory.dormitory_admin where ? like '%?%'";
//        statement.setString(1,key);
//        statement.setString(2,value);
        String sql = "select d.id,b.name,d.name,d.type,d.available,d.telephone from dormitory.building b,dormitory.dormitory d  where d.building_id=b.id and d."+key+" like '%"+value+"%'";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集
        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String name = resultSet.getString(3);
                Integer type = resultSet.getInt(4);
                Integer available = resultSet.getInt(5);
                String telephone =resultSet.getString(6);
                list.add(new Dormitory(id,buildingName,name,type,available,telephone));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }

    @Override
    public List<Dormitory> availableList() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from dormitory.dormitory where available > 0";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集

        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Integer buildingId = resultSet.getInt(2);
                String name = resultSet.getString(3);
                Integer type = resultSet.getInt(4);
                Integer available = resultSet.getInt(5);
                String telephone =resultSet.getString(6);
                list.add(new Dormitory(id,buildingId,name,type,available,telephone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }

    @Override
    public Integer subAvailable(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update dormitory.dormitory set available = available-1 where id ="+id;
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null; //结果集

        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            result= statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer addAvailable(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update dormitory.dormitory set available = available+1 where id ="+id;
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null; //结果集

        List<Dormitory> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            result= statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }
    @Override
    public List<Integer> findDormitoryIdByBuildingId(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql="select id from dormitory.dormitory where building_id ='"+id+"'";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集
        List<Integer> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                id=resultSet.getInt(1);
                list.add(id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement);
        }
        return list;
    }

    @Override
    public Integer availableId() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql="select id from dormitory.dormitory where available >0 limit 0,1";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                result=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql="delete from dormitory.dormitory where  id ="+ id;
        PreparedStatement statement = null; //预编译 防止sql注入
       Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,statement);
        }
        return result;
    }

    @Override
    public Integer save(Dormitory dormitory) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into dormitory.dormitory(building_id,name,type,available,telephone) values(?,?,?,?,?) ";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dormitory.getBuildingId());
            statement.setString(2, dormitory.getName());
            statement.setInt(3, dormitory.getType());
            statement.setInt(4, dormitory.getAvailable());
            statement.setString(5, dormitory.getTelephone());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer update(Dormitory dormitory) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update dormitory.dormitory set name=? ,telephone =? where id = ?";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitory.getName());
            statement.setString(2, dormitory.getTelephone());
            statement.setInt(3, dormitory.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

}
