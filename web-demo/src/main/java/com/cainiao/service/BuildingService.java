package com.cainiao.service;

import com.cainiao.pojo.Building;

import java.sql.SQLException;
import java.util.List;

public interface BuildingService {
    public List<Building> list() throws SQLException;

    public List<Building> search(String key, String value) throws SQLException;

    public void save(String name,String introduction,Integer adminId) throws SQLException;

    public void update(Integer id,String name,String introduction,Integer adminId) throws SQLException;

    public void delete(Integer id) throws SQLException;
}
