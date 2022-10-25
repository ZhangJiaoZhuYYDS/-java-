package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/*线路service*/
public interface RouteService {
    /*根据类别分类查询*/
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize);

}
