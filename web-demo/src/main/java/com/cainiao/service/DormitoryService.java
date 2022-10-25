package com.cainiao.service;

import com.cainiao.pojo.Dormitory;
import com.cainiao.pojo.DormitoryAdmin;

import java.sql.SQLException;
import java.util.List;

public interface DormitoryService {
    public List<Dormitory> availableList() throws SQLException;
    public void save(Dormitory dormitory) throws SQLException;
    public List<Dormitory> list() throws SQLException;
    public List<Dormitory> search(String kry, String value) throws SQLException;
    public void update(Dormitory dormitory) throws  SQLException;
    public void delete(Integer id) throws SQLException;
}
