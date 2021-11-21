package com.kuangstudy.service.blogcategory;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuangstudy.entity.Blog;
import com.kuangstudy.entity.BlogCategory;
import com.kuangstudy.mapper.BlogCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/11/11 20:30
 */
@Service
@Slf4j
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements IBlogCategoryService{

    /**
     * 查询分类
     * @return
     */
    public List<BlogCategory> findBlogCategies(){
        // 1: 查询所有的分类信息
        LambdaQueryWrapper<BlogCategory> queryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的分类
        queryWrapper.eq(BlogCategory::getStatus,1);
        //queryWrapper.orderByAsc(BlogCategory::getSorted);
        // 3: 执行分类查询
        List<BlogCategory> blogCategories = this.list(queryWrapper);
        if(!CollectionUtils.isEmpty(blogCategories)){
            // 用stream排序去取代SQL的排序，换取排序不走索引的问题。
            blogCategories =  blogCategories.stream()
                    .sorted((a,b)->a.getSorted()-b.getSorted())
                    .collect(Collectors.toList());
        }
        return blogCategories;
    }
}
