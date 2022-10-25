package com.cainiao.dao.impl;

import com.cainiao.dao.StudentManagerDao;
import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.StudentManager;
import com.cainiao.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManagerDaoImpl implements StudentManagerDao {
    @Override
    public List<StudentManager> list() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state,s.create_date  from dormitory.student s,dormitory.dormitory d where s.dormitory_id = d.id";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集

        List<StudentManager> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String number = resultSet.getString(2);
                String name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                Integer dormitoryId = resultSet.getInt(5);
                String dormitoryName =resultSet.getString(6);
                String state = resultSet.getString(7);
                String createDate = resultSet.getString(8);
                StudentManager studentManager = new StudentManager(id,number,name,gender,dormitoryId,dormitoryName,state,createDate);
                list.add(studentManager);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }


    @Override
    public List<StudentManager> search(String key,String value) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
//        String sql="select * from dormitory.dormitory_admin where ? like '%?%'";
//        statement.setString(1,key);
//        statement.setString(2,value);
        String sql = "select s.id,s.name,s.number,s.gender,s.dormitory_id,d.name,s.state,s.create_date  from dormitory.student s,dormitory.dormitory d where s.dormitory_id = d.id and s."+key+" like '%"+value+"%'";
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集

        List<StudentManager> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
//            statement.setString(1,key);
//            statement.setString(2,value);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String number = resultSet.getString(2);
                String name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                Integer dormitoryId = resultSet.getInt(5);
                String dormitoryName =resultSet.getString(6);
                String state = resultSet.getString(7);
                String createDate = resultSet.getString(8);
                StudentManager studentManager = new StudentManager(id,number,name,gender,dormitoryId,dormitoryName,state,createDate);
                list.add(studentManager);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }

    @Override
    public Integer save(StudentManager studentManager) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into dormitory.student(number,name,gender,dormitory_id,state,create_date) values(?,?,?,?,?,?) ";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentManager.getNumber());
            statement.setString(2, studentManager.getName());
            statement.setString(3, studentManager.getGender());
            statement.setInt(4, studentManager.getDormitoryId());
            statement.setString(5, studentManager.getState());
            statement.setString(6,studentManager.getCreateDate());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer update(StudentManager studentManager) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update dormitory.student set number=?,name=?,gender=?,dormitory_id=? where id=?";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentManager.getNumber());
            statement.setString(2, studentManager.getName());
            statement.setString(3, studentManager.getGender());
            statement.setInt(4, studentManager.getDormitoryId());
            statement.setInt(5,studentManager.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id ) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "delete  from dormitory.student where id=?";
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
    public List<Integer> findStudentManagerByDormitoryId(Integer id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select s.id from dormitory.student s where s.dormitory_id = "+id;
        PreparedStatement statement = null; //预编译 防止sql注入
        ResultSet resultSet = null; //结果集
        List<Integer> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                list.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return list;
    }

    @Override
    public Integer updateDormitory(Integer studentId, Integer DormitoryId) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "update dormitory.student set dormitory_id=? where id=?";
        PreparedStatement statement = null; //预编译 防止sql注入
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, DormitoryId);
            statement.setInt(2, studentId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, statement);
        }
        return result;
    }


}
