package com.cainiao.service.impl;

import com.cainiao.dao.DormitoryDao;
import com.cainiao.dao.StudentManagerDao;
import com.cainiao.dao.impl.DormitoryDaoImpl;
import com.cainiao.dao.impl.StudentManagerDaoImpl;
import com.cainiao.pojo.Dormitory;
import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.service.DormitoryService;

import java.sql.SQLException;
import java.util.List;

public class DormitoryServiceImpl implements DormitoryService {
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentManagerDao studentManagerDao = new StudentManagerDaoImpl();
    @Override
    public List<Dormitory> availableList() throws SQLException {
        return this.dormitoryDao.availableList();
    }

    @Override
    public void save(Dormitory dormitory) throws SQLException {
        final Integer save = this.dormitoryDao.save(dormitory);
        if (save != 1) throw  new RuntimeException("添加宿舍信息失败");
    }

    @Override
    public List<Dormitory> list() throws SQLException {
        return this.dormitoryDao.list();
    }

    @Override
    public List<Dormitory> search(String key, String value) throws SQLException {
        if(value.equals("")) return this.dormitoryDao.list();
        return this.dormitoryDao.search(key,value);
    }

    @Override
    public void update(Dormitory dormitory) throws SQLException {
        Integer update = this.dormitoryDao.update(dormitory);
        if (update != 1) throw new RuntimeException("修改宿舍信息失败");
    }

    @Override
    public void delete(Integer id) throws SQLException {
        List<Integer> studentManagerIdList = this.studentManagerDao.findStudentManagerByDormitoryId(id); //通过宿舍找宿舍里的所有学生
        for (Integer studentManagerId : studentManagerIdList) { //对学生遍历
            Integer availableId = this.dormitoryDao.availableId(); //找到所有空宿舍
            Integer updateDormitory = this.studentManagerDao.updateDormitory(studentManagerId, availableId); //把学上调到有空宿舍
            Integer subAvailable = this.dormitoryDao.subAvailable(availableId); //有空宿舍减去一个空床位
            if (updateDormitory != 1 || subAvailable != 1) throw new RuntimeException("学生更新宿舍失败");
        }
        Integer delete = this.dormitoryDao.delete(id);
        if (delete !=1) throw new RuntimeException("删除宿舍信息失败");
    }
}
