package com.cainiao.service.impl;

import com.cainiao.dao.DormitoryAdminDao;
import com.cainiao.dao.impl.DormitoryAdminDaoImpl;
import com.cainiao.dto.DormitoryAdminDto;
import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.service.DormitoryAdminService;

import java.sql.SQLException;
import java.util.List;

public class DormitoryAdminServiceImpl implements DormitoryAdminService {

    private DormitoryAdminDao dormitoryAdminDao = new DormitoryAdminDaoImpl();
    @Override
    public List<DormitoryAdmin> list() throws SQLException {
        return this.dormitoryAdminDao.list();
    }

    @Override
    public List<DormitoryAdmin> search(String key, String value) throws SQLException {
        if(value.equals("")) return this.dormitoryAdminDao.list();
        return this.dormitoryAdminDao.search(key,value);
    }
    public void save(DormitoryAdmin dormitoryAdmin) throws SQLException {
        Integer save = this.dormitoryAdminDao.save(dormitoryAdmin);
        if (save!=1) {
            throw new RuntimeException("宿管信息添加失败");
        }

    }

    @Override
    public void update(DormitoryAdmin dormitoryAdmin) throws SQLException {
         Integer update = this.dormitoryAdminDao.update(dormitoryAdmin);
        if (update!=1) {
            throw new RuntimeException("宿管信息更新失败");
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Integer delete = this.dormitoryAdminDao.delete(id);
        if (delete!=1) {
            throw new RuntimeException("宿管信息删除失败");
        }
    }

    @Override
    public DormitoryAdminDto login(String username, String password) throws SQLException {
         DormitoryAdmin login = this.dormitoryAdminDao.login(username);
        DormitoryAdminDto dormitoryAdminDto = new DormitoryAdminDto();
        if (login == null){
            dormitoryAdminDto.setCode(-1);  //用户名不存在设为-1  密码错误设为0  登录成功设为 1
        }else {
            if (!login.getPassword().equals(password)){
                dormitoryAdminDto.setCode(0);
            }else{
                dormitoryAdminDto.setCode(1);
                dormitoryAdminDto.setDormitoryAdmin(login);
            }
        }
        return dormitoryAdminDto;
    }

}
