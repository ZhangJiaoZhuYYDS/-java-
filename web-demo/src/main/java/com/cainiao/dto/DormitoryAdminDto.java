package com.cainiao.dto;

import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.SystemAdmin;

/*用来封装查询结果信息*/
public class DormitoryAdminDto {
    private Integer code;
    private DormitoryAdmin dormitoryAdmin;

    @Override
    public String toString() {
        return "DormitoryAdminDto{" +
                "code=" + code +
                ", dormitoryAdmin=" + dormitoryAdmin +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DormitoryAdmin getDormitoryAdmin() {
        return dormitoryAdmin;
    }

    public void setDormitoryAdmin(DormitoryAdmin dormitoryAdmin) {
        this.dormitoryAdmin = dormitoryAdmin;
    }
}
