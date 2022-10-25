package com.cainiao.service;

import com.cainiao.pojo.StudentManager;

import java.sql.SQLException;
import java.util.List;

public interface StudentManagerService {
    public List<StudentManager> list() throws SQLException;
    public List<StudentManager> search(String key,String value) throws SQLException;
    public void save(StudentManager studentManager) throws SQLException;
    public void update(StudentManager studentManager,Integer oldDormitoryId) throws SQLException;
    public void delete(Integer id,Integer dormitoryId) throws SQLException;

}
