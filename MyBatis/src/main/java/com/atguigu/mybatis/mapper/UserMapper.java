package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Package: com.atguigu.mybatis.mapper
 * Description:
 *
 * @Author AQiu
 * @Create 21/06/2023 22:41
 */
@Mapper
public interface UserMapper {
    User selectById(Integer id);
}
