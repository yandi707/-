package com.kuangstudy.service.archives;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuangstudy.entity.Blog;
import com.kuangstudy.mapper.BlogMapper;
import com.kuangstudy.service.blog.IBlogService;
import com.kuangstudy.vo.ArticleWithYear;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/11/17 23:04.
 * Update Date Time:
 *
 * @see
 */
@Service
@Slf4j
public class ArchivesServiceImpl implements IArchivesService {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Map<String,Object>>  queryBlogsByYear() {
        // 1: 查询所有的年份
        List<String> blogList = blogMapper.findBlogYearGroup();
        // 2: 根据年份查询对应年份的文章并且分页
        return blogList.stream().map(year->{
            Map<String,Object> map = new HashMap<>();
            // 3: 根据年份查询对应的每个年份的前10条数据
            IPage<Blog> childrenBlogPage = findChildrenBlogByYear(Integer.parseInt(year), 1, 10);
            map.put("year",year);
            map.put("total",childrenBlogPage.getTotal());
            map.put("pages",childrenBlogPage.getPages());
            map.put("current",childrenBlogPage.getCurrent());
            map.put("pageSize",childrenBlogPage.getSize());
            map.put("blogList", CollectionUtils.isEmpty(childrenBlogPage.getRecords())?new ArrayList<>():childrenBlogPage.getRecords());
            return map;
        }).collect(Collectors.toList());
    }

    public IPage<Blog> findChildrenBlogByYear(Integer year, int pageNo, int pageSize){
        // 1: 创建分页
        Page<Blog> page = new Page<>(pageNo,pageSize);
        // 2: 设定查询条件
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DATE_FORMAT( kb.create_time, '%Y' )",year);
        queryWrapper.eq("kb.status",1);
        queryWrapper.eq("kb.is_delete",0);
        queryWrapper.orderByDesc("kb.create_time");
        // 3: 自动查询并分页
        IPage<Blog> childrenBlogByYear = blogMapper.findChildrenBlogByYear(page, queryWrapper);
        return childrenBlogByYear;
    }



    @Override
    public List<Map<String, Object>>  queryBlogs() {
        // 1:按需加载字段
        //LambdaQueryWrapper<Blog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //lambdaQueryWrapper.select(Blog::getId,Blog::getTitle,Blog::getCreateTime);
        // 1：排除加载字段，把不需要的字段排除
        //List<Blog> list = blogService.list(lambdaQueryWrapper);
        LambdaQueryWrapper<Blog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Blog::getStatus, 1);
        lambdaQueryWrapper.select(Blog.class, s -> (!s.getColumn().equals("content") && !s.getColumn().equals("status")));
        // 2：查询表的发布的文章数据
        List<Blog> blogList = blogService.list(lambdaQueryWrapper);
        // 3: 使用 stream的分组功能进行数据处理
        // treemap确实拥有排序的功能，但是它排序只能在初始化的时候就必须先确认，否则无效
        Map<Integer, List<Blog>> collect = blogList.stream().collect(Collectors.groupingBy(blog -> blog.getCreateTime().getYear()));

        List<Map<String, Object>> collect1 = collect.entrySet().stream().map(entry -> {
            Map<String, Object> map = new HashMap<>();
            map.put("year", entry.getKey());
            map.put("articlesList", entry.getValue());
            return map;
        }).sorted((a, b) -> (int)b.get("year") - (int)a.get("year")).collect(Collectors.toList());

        return collect1;
    }




    @Override
    public List<ArticleWithYear> queryBlogs2() {
        // 1:按需加载字段
        //LambdaQueryWrapper<Blog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //lambdaQueryWrapper.select(Blog::getId,Blog::getTitle,Blog::getCreateTime);
        // 1：排除加载字段，把不需要的字段排除
        //List<Blog> list = blogService.list(lambdaQueryWrapper);
        LambdaQueryWrapper<Blog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Blog::getStatus, 1);
        lambdaQueryWrapper.select(Blog.class, s -> (!s.getColumn().equals("content") && !s.getColumn().equals("status")));
        // 2：查询表的发布的文章数据
        List<Blog> blogList = blogService.list(lambdaQueryWrapper);
        // 3:根据年份分组好的数据,但是没有排序
        Map<Integer, List<Blog>> collect = blogList.stream().collect(Collectors.groupingBy(blog -> blog.getCreateTime().getYear()));
        // 4: stream流化处理
        List<ArticleWithYear> collect1 = collect.entrySet().stream().map(entry -> {
            // 1:创建vo对象
            ArticleWithYear articleWithYear = new ArticleWithYear();
            articleWithYear.setYear(entry.getKey());
            // 2: 什么这里要用map,按需加载返回页面。如果你不考虑  articleWithYear.setArticlesList(entry.getValue());
            List<Blog> blogList1 = entry.getValue();
            List<Map<String, Object>> collect2 = blogList1.stream().map(blog -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", blog.getId());
                map.put("title", blog.getTitle());
                map.put("ctime", blog.getCreateTime());
                return map;
            }).collect(Collectors.toList());
            articleWithYear.setArticlesList(collect2);
            // 3：返回vo
            return articleWithYear;
        }).sorted((a, b) -> b.getYear() - a.getYear()).collect(Collectors.toList());

        return collect1;
    }

}
