package com.oyzc.boot;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oyzc.boot.bean.User;
import com.oyzc.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class Boot05Web01ApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired(required = false)
    UserService userService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
        System.out.println(aLong);
        System.out.println("数据源类型："+dataSource.getClass());
    }

    @Test
    void testUserMapper() {
        User user = userService.getById(1L);
        System.out.println(user);
        List<User> list = userService.list();
        System.out.println(list);
        //分页查询，查询第1页，每页2条数据
        Page<User> userPage = new Page<>(1, 2);
        Page<User> page = userService.page(userPage, null);
        //获取当前页
        long current = page.getCurrent();
        //获取总页数
        long pages = page.getPages();
        //获取数据总数
        long total = page.getTotal();
        System.out.printf("当前第%s页，总计%s页，共%s条记录\n",current,pages,total);
        List<User> records = page.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    @Test
    void testRedis() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello","redis");
        String hello = operations.get("hello");
        System.out.println(hello);
    }

    @Test
    void testRedisConnectionFactory() {
        System.out.println(redisConnectionFactory.getClass());
    }

}
