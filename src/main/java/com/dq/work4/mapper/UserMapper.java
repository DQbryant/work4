package com.dq.work4.mapper;

import com.dq.work4.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserMapper {
    @Select("select * from user where username=#{0}")
    User login(String username);
}
