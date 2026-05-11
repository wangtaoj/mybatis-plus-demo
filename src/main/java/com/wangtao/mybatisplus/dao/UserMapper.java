package com.wangtao.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wangtao.mybatisplus.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author wangtao
 * Created at 2026-05-11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Update("update user set age = #{age}, update_time = #{updateTime} where id = #{id}")
    int updateAgeById(User user);

    @Update("update user set age = #{et.age}, update_time = #{et.updateTime} where id = #{et.id}")
    int updateAgeByIdWithParamName(@Param(Constants.ENTITY) User user);

}
