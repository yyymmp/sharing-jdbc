package com.saimo.sharingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jlz
 * @className: User
 * @date 2021/6/25 11:05
 * @description
 **/
@TableName("t_user")
@Data
public class User {
    private Long userId;
    private String username;
    private String status;
}
