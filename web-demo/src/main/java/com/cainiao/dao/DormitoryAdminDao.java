package com.cainiao.dao;

import com.cainiao.pojo.DormitoryAdmin;

import java.sql.SQLException;
import java.util.List;

public interface DormitoryAdminDao {
    public List<DormitoryAdmin> list() throws SQLException;
    public List<DormitoryAdmin> search(String key,String value) throws SQLException;
    public Integer save(DormitoryAdmin dormitoryAdmin) throws SQLException;
    public Integer update(DormitoryAdmin dormitoryAdmin) throws SQLException;
    public Integer delete(Integer id) throws SQLException;
    public Integer register(String username,String password) throws SQLException;
    public DormitoryAdmin login(String username) throws SQLException;
}
