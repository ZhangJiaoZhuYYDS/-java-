package com.cainiao.service.impl;

import com.cainiao.dao.DormitoryDao;
import com.cainiao.dao.StudentManagerDao;
import com.cainiao.dao.impl.DormitoryDaoImpl;
import com.cainiao.dao.impl.StudentManagerDaoImpl;
import com.cainiao.pojo.StudentManager;
import com.cainiao.service.StudentManagerService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentManagerServiceImpl implements StudentManagerService {
    private StudentManagerDao studentManagerDao = new StudentManagerDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    @Override
    public List<StudentManager> list() throws SQLException {
        return studentManagerDao.list();
    }

    @Override
    public List<StudentManager> search(String key,String value) throws SQLException {
        return this.studentManagerDao.search(key, value);
    }

    @Override
    public void save(StudentManager studentManager) throws SQLException {
        studentManager.setState("入住");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        studentManager.setCreateDate(simpleDateFormat.format(date));
        Integer save=this.studentManagerDao.save(studentManager);
        //添加新人减少空余床位
        Integer sub = this.dormitoryDao.subAvailable(studentManager.getDormitoryId());
        if (save !=1 || sub !=1) throw new RuntimeException("添加学生信息失败");
    }

    @Override
    public void update(StudentManager studentManager,Integer oldDormitoryId) throws SQLException {

        Integer update = this.studentManagerDao.update(studentManager);

        // 原宿舍available+1,新宿舍available-1
        Integer dormitory1 = this.dormitoryDao.addAvailable(oldDormitoryId);
        Integer dormitory2 = this.dormitoryDao.subAvailable(studentManager.getDormitoryId());
        if (update != 1 || dormitory1 != 1 || dormitory2 !=1) throw new RuntimeException("更新学生信息失败！");
    }

    @Override
    public void delete(Integer id,Integer dormitoryId) throws SQLException {
        Integer delete = this.studentManagerDao.delete(id);
        // 有人搬出宿舍，空床+1
        Integer available=this.dormitoryDao.addAvailable(dormitoryId);
        if(delete !=1 || available != 1) throw new RuntimeException("删除学生信息失败");
    }
}
