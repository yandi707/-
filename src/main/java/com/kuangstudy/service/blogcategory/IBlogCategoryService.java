package com.kuangstudy.service.blogcategory;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kuangstudy.entity.BlogCategory;

import java.util.List;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/11/11 20:29
 */
public interface IBlogCategoryService extends IService<BlogCategory> {

    /**
     * 查询首页分类
     * @return
     */
    List<BlogCategory> findBlogCategies();
}
