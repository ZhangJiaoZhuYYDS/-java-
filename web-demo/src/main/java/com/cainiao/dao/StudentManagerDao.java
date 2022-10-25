package com.cainiao.dao;

import com.cainiao.pojo.StudentManager;

import java.sql.SQLException;
import java.util.List;

public interface StudentManagerDao {
    public List<StudentManager> list() throws SQLException;
    public List<StudentManager> search(String key , String value) throws SQLException;
    public Integer save(StudentManager studentManager) throws SQLException;
    public Integer update(StudentManager studentManager) throws SQLException;
    public Integer delete(Integer id) throws SQLException;
    public List<Integer> findStudentManagerByDormitoryId(Integer id) throws SQLException;
    public Integer updateDormitory(Integer studentId,Integer DormitoryId) throws SQLException;
}
