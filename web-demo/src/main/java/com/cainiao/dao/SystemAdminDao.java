package com.cainiao.dao;

import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.SystemAdmin;

import java.sql.SQLException;

public interface SystemAdminDao {
    public SystemAdmin findByUsername(String username) throws SQLException;
    public DormitoryAdmin DormitoryFindByUsername(String domitoryUsername) throws SQLException;
    public Integer dormitorySave(String dormitoryUsername,String dormitoryPassword, String dormitoryName, String dormitoryGender, String dormitoryTelephone) throws SQLException;
}
