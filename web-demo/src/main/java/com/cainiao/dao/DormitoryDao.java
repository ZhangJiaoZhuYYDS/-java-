package com.cainiao.dao;

import com.cainiao.pojo.Dormitory;
import com.cainiao.pojo.DormitoryAdmin;

import java.sql.SQLException;
import java.util.List;

public interface DormitoryDao {
    public List<Dormitory> list() throws SQLException;
    public List<Dormitory> search(String key, String value) throws SQLException;
    public List<Dormitory> availableList() throws SQLException;
    public Integer subAvailable(Integer id) throws SQLException;
    public Integer addAvailable(Integer id) throws SQLException;
    public List<Integer> findDormitoryIdByBuildingId(Integer id) throws SQLException;
    public Integer availableId() throws SQLException;
    public Integer delete(Integer id) throws SQLException;
    public Integer save(Dormitory dormitory) throws SQLException;
    public Integer update(Dormitory dormitory) throws SQLException;

}
