package com.saimo.sharingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.saimo.sharingjdbc.entity.Course;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface CourseMapper extends BaseMapper<Course> {
}
