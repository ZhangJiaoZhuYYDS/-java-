package com.cainiao.service.impl;

import com.cainiao.dao.BuildingDao;
import com.cainiao.dao.DormitoryDao;
import com.cainiao.dao.StudentManagerDao;
import com.cainiao.dao.impl.BuildingDaoImpl;
import com.cainiao.dao.impl.DormitoryDaoImpl;
import com.cainiao.dao.impl.StudentManagerDaoImpl;
import com.cainiao.pojo.Building;
import com.cainiao.pojo.Dormitory;
import com.cainiao.service.BuildingService;

import java.sql.SQLException;
import java.util.List;

public class BuildingServiceImpl implements BuildingService {
    private  BuildingDao buildingDao = new BuildingDaoImpl();
    private DormitoryDao dormitoryDao =new DormitoryDaoImpl();
    private StudentManagerDao studentManagerDao = new StudentManagerDaoImpl();
    @Override
    public List<Building> list() throws SQLException {
        return this.buildingDao.list();
    }

    @Override
    public List<Building> search(String key, String value) throws SQLException {
        if (value.equals("")) return this.buildingDao.list();
        return this.buildingDao.search(key, value);
    }

    @Override
    public void save(String name, String introduction, Integer adminId) throws SQLException {
        Integer save = this.buildingDao.save(name,introduction,adminId);
        if (save!=1) {
            throw new RuntimeException("宿管信息添加失败");
        }
    }

    @Override
    public void update(Integer id, String name, String introduction, Integer adminId) throws SQLException {
        Integer update = this.buildingDao.update(id, name, introduction, adminId);
        if (update != 1) throw new RuntimeException("更新樓宇信息失敗");
    }


    @Override
    public void delete(Integer id) throws SQLException {
        //學生換宿舍
         List<Integer> dormitoryIdList = this.dormitoryDao.findDormitoryIdByBuildingId(id);//通过宿舍楼找里面的所有宿舍
        for (Integer dormitoryId : dormitoryIdList) {  //遍历每个宿舍
            List<Integer> studentManagerIdList = this.studentManagerDao.findStudentManagerByDormitoryId(dormitoryId); //通过宿舍找宿舍里的所有学生
            for (Integer studentManagerId : studentManagerIdList) { //对学生遍历
                Integer availableId = this.dormitoryDao.availableId(); //找到所有空宿舍
                Integer updateDormitory = this.studentManagerDao.updateDormitory(studentManagerId, availableId); //把学上调到有空宿舍
                Integer subAvailable = this.dormitoryDao.subAvailable(availableId); //有空宿舍减去一个空床位
                if (updateDormitory != 1 || subAvailable != 1) throw new RuntimeException("学生更新宿舍失败");
            }
        }
        // 删除宿舍
        for (Integer integer : dormitoryIdList) {
             Integer delete = this.dormitoryDao.delete(integer);
            if (delete != 1) throw new RuntimeException("宿舍信息删除失敗");
        }
        //删除楼宇
        Integer delete = this.buildingDao.delete(id);
        if (delete !=1 ) throw new RuntimeException("楼宇信息删除失败");
    }


}
