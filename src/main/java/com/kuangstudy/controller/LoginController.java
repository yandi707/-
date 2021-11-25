package com.kuangstudy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kuangstudy.UserVo;
import com.kuangstudy.config.KConstants;
import com.kuangstudy.entity.User;
import com.kuangstudy.service.user.IUserService;
import com.kuangstudy.utils.pwd.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/11/12 22:56
 */
@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 跳页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login(HttpSession session) {
        // 1:获取用户登录的信息
        User user = (User) session.getAttribute(KConstants.SESSION_USER);
        // 2:如果已经登录
        if (user != null) {
            // 3:直接服务器端重定向到首页
            return "redirect:/";
        }
        // 4:否则代表没有登录，直接去登录页面
        return "login";
    }

    /**
     * 退出
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 注销所有的session
        session.invalidate();
        // 2:否则代表没有登录，直接去登录页面
        return "redirect:/login";
    }

    /**
     * 处理登录逻辑
     *
     * @param userVo
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/logined")
    public String logined(@RequestBody UserVo userVo, HttpSession session) {
        System.out.println("进入到方法了哦");
        //1: 根据账号去查询用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getTelephone, userVo.getAccount());
        User user = userService.getOne(lambdaQueryWrapper);
        // 2: 如果用户存在直接抛异常
        if (user == null) {
            return "fail";
        }
        // 3：把用户输入的密码进行加密
        String inputpwd = MD5Util.md5slat(userVo.getPassword());
        // 4：然后把用户输入的密码进行加密和数据库已经加密的密码进行比较，如果一致，就说明是正确的。
        if (user.getPassword().equals(inputpwd)) {
            // 5: 正确的话，就把登录的用户信息放到session中。
            session.setAttribute(KConstants.SESSION_USER, user);
            return "success";
        }

        // 6：不一致用户密码不存在
        return "fail";
    }

    /**
     * 异步获取session中用户信息
     *
     * @return
     */
    @PostMapping("/login/loadsession")
    @ResponseBody
    public User toSessionUser(HttpSession session) {
        return (User) session.getAttribute(KConstants.SESSION_USER);
    }
}
