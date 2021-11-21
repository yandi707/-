package com.kuangstudy.controller;

import com.kuangstudy.entity.BlogCategory;
import com.kuangstudy.service.blogcategory.IBlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/11/11 21:49
 */
@RestController
public class BlogCategoryController {

    @Autowired
    private IBlogCategoryService blogCategoryService;

    /**
     * 加载分类数据
     *
     * @return
     */
    @GetMapping("/api/blogcategory/load")
    public List<BlogCategory> findCategories() {
        return blogCategoryService.findBlogCategies();
    }

}
