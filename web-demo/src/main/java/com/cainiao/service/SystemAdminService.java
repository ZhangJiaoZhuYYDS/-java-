package com.cainiao.service;

import com.cainiao.dto.DormitoryAdminDto;
import com.cainiao.dto.SystemAdminDto;
import com.cainiao.pojo.Dormitory;
import com.cainiao.pojo.DormitoryAdmin;

import java.sql.SQLException;

public interface SystemAdminService {
    public SystemAdminDto login(String username,String password) throws SQLException;
    public DormitoryAdminDto dormitoryCheckUsername(String username) throws SQLException;
    public Integer dormitorySave(String dormitoryUsername,String dormitoryPassword,String dormitoryName,String dormitoryGender,String dormitoryTelephone) throws SQLException;
}
