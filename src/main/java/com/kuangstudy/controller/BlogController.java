package com.kuangstudy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuangstudy.entity.Blog;
import com.kuangstudy.service.blog.IBlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/11/12 20:19
 */
@RestController
@Slf4j
public class BlogController {


    @Autowired
    private IBlogService blogService;


    /**
     * 查询博客内容信息
     *
     * @return
     */
    @GetMapping("/api/blog/load")
    public Page<Blog> findBlogs(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false, defaultValue = "0") Integer cid) {
        // 1:设置分页起始和每页显示多少条
        Page page = new Page<>(pageNo, pageSize);
        // 2:设置条件
        LambdaQueryWrapper<Blog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 3：查询 status= 1 发布并且 isdelete=0未删除， 根据创建时间排序 desc
        lambdaQueryWrapper
                // 如果用户不传递cid或者传递的是0代表查询的全部
                .eq(cid != null && !cid.equals(0), Blog::getCategoryId, cid)
                .eq(Blog::getStatus, 1)
                .eq(Blog::getIsDelete, 0)
                .orderByDesc(Blog::getCreateTime);
        // 4: 执行分页
        Page blogPage = blogService.page(page, lambdaQueryWrapper);
        // 5: 返回分页
        return blogPage;
    }


}
