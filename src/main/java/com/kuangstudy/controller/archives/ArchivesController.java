package com.kuangstudy.controller.archives;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuangstudy.service.archives.IArchivesService;
import com.kuangstudy.vo.ArticleWithYear;

/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/11/16 22:30.
 * Update Date Time:
 *
 * @see
 */
@Controller
public class ArchivesController {


    @Autowired
    private IArchivesService archivesService;


    /**
     * 归档首页
     *
     * @return
     */
    @GetMapping(value = {"/archives", "/acv"})
    public ModelAndView toArchives() {
        // 1: 创建modelandview实例
        ModelAndView modelAndView = new ModelAndView();
        // 2: 指定返回的页面视图
        modelAndView.setViewName("archives/index");
        // 3: 返回
        return modelAndView;
    }


    /**
     * 查询文章归档数据
     * @return
     */
    @GetMapping("/archives/load")
    @ResponseBody
    public List<Map<String, Object>>  queryBlogs() {
        List<Map<String, Object>>  integerListMap = archivesService.queryBlogs();//jackson序列化的时候已经发生改变
        return integerListMap;
    }

    /**
     * 查询文章归档数据
     * @return
     */
    @GetMapping("/archives/load3")
    @ResponseBody
    public List<Map<String,Object>>  queryBlogs3() {
        return archivesService.queryBlogsByYear();
    }

    /**
     * 查询文章归档数据
     * @return
     */
    @GetMapping("/archives/load2")
    @ResponseBody
    public List<ArticleWithYear> queryBlogs2() {
        return archivesService.queryBlogs2();
    }
}
