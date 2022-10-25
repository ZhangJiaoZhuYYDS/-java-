package com.cainiao.dto;

import com.cainiao.pojo.DormitoryAdmin;
import com.cainiao.pojo.SystemAdmin;

/*用来封装查询结果信息*/
public class SystemAdminDto {
    private Integer code;
    private SystemAdmin systemAdmin;

    @Override
    public String toString() {
        return "SystemAdminDto{" +
                "msg='" + code + '\'' +
                ", systemAdmin=" + systemAdmin +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SystemAdmin getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(SystemAdmin systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

}
