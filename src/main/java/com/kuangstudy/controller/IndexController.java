package com.kuangstudy.controller;

import com.kuangstudy.config.KConstants;
import com.kuangstudy.entity.User;
import com.kuangstudy.service.blogcategory.IBlogCategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Description: 首页controller
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/11/9 21:57.
 * Update Date Time:
 *
 * @see
 */
@Controller
@Log4j2
public class IndexController {

    @Autowired
    private IBlogCategoryService blogCategoryService;

    /**
     * Description: 首页
     * @throws Exception
     * @date 2021/11/9
     * @author yykk
     */
    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(String cid, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        // 1:获取用户登录的信息
        User user = (User)session.getAttribute(KConstants.SESSION_USER);
        // 2:如果没有登录
        if(user==null){
            // 3:直接服务器端重定向到登录进行登录
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        // 4: 把查询分类放入到数据模型中
        modelAndView.addObject("blogCategories", blogCategoryService.findBlogCategies());
        modelAndView.addObject("cid", Optional.ofNullable(cid).orElse("0"));
        // 5: 跳转视图模板
        modelAndView.setViewName("index");
        return modelAndView;
    }


    /**
     * Description: 首页
     * @throws Exception
     * @date 2021/11/9
     * @author yykk
     */
    @GetMapping("/newsindex")
    public ModelAndView newindex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newsindex");
        return modelAndView;
    }


}
