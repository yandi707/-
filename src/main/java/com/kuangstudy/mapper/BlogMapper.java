package com.kuangstudy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kuangstudy.entity.Blog;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper extends BaseMapper<Blog> {

    //IPage<Blog> findByPage(IPage<Blog> page, @Param(Constants.WRAPPER) Wrapper<Blog> wrapper);
}
