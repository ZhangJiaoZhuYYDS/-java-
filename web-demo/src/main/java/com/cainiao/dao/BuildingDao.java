package com.cainiao.dao;

import com.cainiao.pojo.Building;
import com.cainiao.pojo.Dormitory;

import java.sql.SQLException;
import java.util.List;
import java.util.Queue;

public interface BuildingDao {
    public List<Building> list() throws SQLException;

    public List<Building> search(String key ,String value) throws SQLException;

    public Integer save(String name,String introduction,Integer adminId) throws SQLException;
    public Integer update(Integer id,String name,String introduction,Integer adminId) throws SQLException;
    public Integer delete(Integer id)throws SQLException;


}
