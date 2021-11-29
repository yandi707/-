package com.kuangstudy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kuangstudy.entity.Blog;

public interface BlogMapper extends BaseMapper<Blog> {

    IPage<Blog> findChildrenBlogByYear(IPage<Blog> page, @Param(Constants.WRAPPER) Wrapper<Blog> wrapper);

    /**
     * 分组查询对应的年份
     * @return
     */
    List<String> findBlogYearGroup();

}
