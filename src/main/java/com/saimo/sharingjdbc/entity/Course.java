package com.saimo.sharingjdbc.entity;

import lombok.Data;

/**
 * @author jlz
 * @className: Course
 * @date 2021/6/25 9:47
 * @description
 **/
@Data
public class Course {
    private Long cid;
    private String cname;
    private Long userId;
    private String status;
}
