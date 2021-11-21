package com.kuangstudy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuangstudy.entity.Blog;
import com.kuangstudy.enums.StatusEnum;
import com.kuangstudy.entity.User;
import com.kuangstudy.mapper.BlogMapper;
import com.kuangstudy.service.user.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class KssWebProjectApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    public void testSelect() {
        User user = new User();
        user.setId(2);
        user.setNickname("xiaowenwne");
        user.setPassword("3423423423");
        user.setSign("我是一个小可爱");
        userService.saveOrUpdate(user);

        Object obj1 = true ? new Integer(1) : new Double(2.0);
        System.out.println(obj1);
    }


    @Test
    public void testpage(){
        // 1、设置分页
        Page<User> page = new Page<>(2,2);
        // 2：设置条件
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 3：查询分页返回
        Page<User> userPage = userService.page(page, lambdaQueryWrapper);
        // 这个userService.page 第一步：发起一个select * from user limit 1,2
        // 这个userService.page 第二步：发起一个 select count(1) from (select * from user)
        System.out.println(userPage.getTotal());
        System.out.println(userPage.getPages());
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }


    @Resource
    private BlogMapper blogMapper;

    @Test
    public void testsave(){
        Blog blog = new Blog();
        blog.setTitle("111111111111111");
        blog.setIsDelete(0);
        blog.setStatus(StatusEnum.HIGH);
        blogMapper.insert(blog);
    }



    @Test
    public void removeblog(){
        blogMapper.deleteById(2);
    }











}
