package com.cainiao.service;

import com.cainiao.dto.DormitoryAdminDto;
import com.cainiao.pojo.DormitoryAdmin;

import java.sql.SQLException;
import java.util.List;

public interface DormitoryAdminService {
    //查询所有
    public List<DormitoryAdmin> list() throws SQLException;
    //条件查询
    public List<DormitoryAdmin> search(String kry,String value) throws SQLException;
    //添加新
    public void save(DormitoryAdmin dormitoryAdmin) throws SQLException;
    //修改
    public void update(DormitoryAdmin dormitoryAdmin) throws SQLException;
    //删除
    public void delete(Integer id) throws SQLException;
    //注册
    public DormitoryAdminDto login(String username, String password) throws SQLException;

}
