package com.cainiao.service.impl;

import com.cainiao.dao.DormitoryDao;
import com.cainiao.dao.SystemAdminDao;
import com.cainiao.dao.impl.SystemAdminDaoImpl;
import com.cainiao.dto.DormitoryAdminDto;
import com.cainiao.dto.SystemAdminDto;
import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.SystemAdmin;
import com.cainiao.service.SystemAdminService;

import java.sql.SQLException;

public class SystemAdminServiceImpl implements SystemAdminService {
     private SystemAdminDao systemAdminDao = new SystemAdminDaoImpl();
    @Override
    public SystemAdminDto login(String username, String password) throws SQLException {
        // 1 通过username查询数据库
        // 2 查询结果为空，返回username错误
        // 3 查询结果不为空，再判断password是否正确

//        System.out.println("this是"+this);
        SystemAdmin systemAdmin = this.systemAdminDao.findByUsername(username);
//        System.out.println("systemAdmin是"+systemAdmin);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if (systemAdmin == null){
            systemAdminDto.setCode(-1);  //用户名不存在设为-1  密码错误设为0  登录成功设为 1
        }else {
            if (!systemAdmin.getPassword().equals(password)){
               systemAdminDto.setCode(0);
            }else{
                systemAdminDto.setCode(1);
                systemAdminDto.setSystemAdmin(systemAdmin);
            }
        }
        return systemAdminDto;
    }
    /*检测楼管登录账号*/
    @Override
    public DormitoryAdminDto dormitoryCheckUsername(String dormitoryUsername) throws SQLException {
        DormitoryAdmin dormitoryAdmin = systemAdminDao.DormitoryFindByUsername(dormitoryUsername);
        DormitoryAdminDto dormitoryAdminDto = new DormitoryAdminDto();
        if (dormitoryAdmin == null){
            dormitoryAdminDto.setCode(1);  //用户名不存在设为1  存在设为0
        }else {
           dormitoryAdminDto.setCode(0);
        }
        return dormitoryAdminDto;
    }

    @Override
    public Integer dormitorySave(String dormitoryUsername, String dormitoryPassword, String dormitoryName, String dormitoryGender, String dormitoryTelephone) throws SQLException {
        Integer integer = systemAdminDao.dormitorySave(dormitoryUsername, dormitoryPassword, dormitoryName, dormitoryGender, dormitoryTelephone);
        return integer;
    }
}
