package com.kuangstudy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuangstudy.config.KConstants;
import com.kuangstudy.entity.Blog;
import com.kuangstudy.entity.User;
import com.kuangstudy.service.blog.IBlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/11/12 20:19
 */
@Controller
@Slf4j
public class BlogDetailController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/detail/{blogId}")
    public ModelAndView blogdetail(@PathVariable("blogId") Integer blogId, HttpSession session) {
        // 1； 定义数据模型
        ModelAndView modelAndView = new ModelAndView();
        // 2:获取用户登录的信息
        User user = (User) session.getAttribute(KConstants.SESSION_USER);
        // 3:如果没有登录
        if (user == null) {
            // 4:直接服务器端重定向到登录进行登录
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        // 5: 根据id查询
        Blog blog = blogService.getById(blogId);
        // 6: 把数据放入数据模型中
        modelAndView.addObject("blog", blog);
        // 7: 把视图放入进去 ,注意：这里blog前面千万不要加/否则找不到页面
        modelAndView.setViewName("/blog/detail");
        return modelAndView;
    }


}
