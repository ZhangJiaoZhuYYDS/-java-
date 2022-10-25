package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao =new UserDaoImpl(); //创建Userdao对象

    /*注册用户*/
    @Override
    public boolean register(User user) {

        // 1 根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if(u != null){
            //用户名存在，注册失败
            return false;
        }
        // 2 保存用户信息
        userDao.save(user);
        return true;
    }
    /*用户登录*/
    @Override
    public User login(User user) {

        // 1 根据用户名和密码验证用户
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }


}
