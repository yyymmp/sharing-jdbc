package com.saimo.sharingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saimo.sharingjdbc.entity.Course;
import com.saimo.sharingjdbc.entity.Dict;
import com.saimo.sharingjdbc.entity.User;
import com.saimo.sharingjdbc.mapper.CourseMapper;
import com.saimo.sharingjdbc.mapper.DictMapper;
import com.saimo.sharingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DictMapper dictMapper;

    /********************** 水平分表 ***********************/
    @Test
    void contextLoads() {
        Course course = new Course();
        //cid由我们设置的策略，雪花算法进行生成
        course.setCname("Java");
        course.setUserId(100L);
        course.setStatus("Normal");
        courseMapper.insert(course);
    }

    @Test
    public void findCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 536248443081850881L);
        courseMapper.selectOne(wrapper);
    }

    /********************** 水平分库 ***********************/
    @Test
    public void addCourse() {
        Course course = new Course();
        //cid由我们设置的策略，雪花算法进行生成  分表根据cid
        course.setCname("python");
        //分库根据user_id
        course.setUserId(100L);
        course.setStatus("Normal");
        courseMapper.insert(course);

        course.setCname("c++");
        course.setUserId(111L);
        courseMapper.insert(course);
    }

    /************************ 垂直分库 ***********************/
    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("Jack");
        user.setStatus("Normal");
        userMapper.insert(user);
    }

    @Test
    public void findUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 536472243283165185L);
        System.out.println(userMapper.selectOne(wrapper));
    }

    /**********************公共表测试*****************/
    @Test
    public void addDict() {
        Dict dict = new Dict();
        dict.setStatus("Normal");
        dict.setValue("启用");
        dictMapper.insert(dict);
    }

    @Test
    public void deleteDict() {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_id", 615196432269312001L);
        System.out.println(dictMapper.selectOne(wrapper));
    }

}
