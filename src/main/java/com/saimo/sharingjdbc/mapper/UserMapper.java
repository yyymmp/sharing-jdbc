package com.saimo.sharingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.saimo.sharingjdbc.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}