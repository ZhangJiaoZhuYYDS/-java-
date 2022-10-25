package com.cainiao.web.request;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class teat {
        public static void main(String[] args){
            Map map = new HashMap();  //定义Map集合
            map.put("apple","新鲜的苹果");  //向集合中添加元素
            map.put("computer","配置优良的计算机");
            map.put("book","堆积成山的图书");
            map.put("time",new Date());  //向集合中添加时间对象
            Set keySet = map.keySet();  //获取key集合对象
            System.out.println(keySet);
            for(Object keyName:keySet){
                System.out.println("键名:"+keyName);  //输出键名
            }
        }
    }

