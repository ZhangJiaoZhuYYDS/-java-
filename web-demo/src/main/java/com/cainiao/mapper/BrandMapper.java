package com.cainiao.mapper;

import com.cainiao.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrandMapper {

    /*查询所有方法*/
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /*添加方法*/
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /*修改方法  第一步 数据回显 根据ID查询 */
    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int id);

    /*修改方法 第二步 */
    @Update("update tb_brand set brand_name= #{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void update(Brand brand);
}
