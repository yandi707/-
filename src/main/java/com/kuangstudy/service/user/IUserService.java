package com.kuangstudy.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kuangstudy.entity.User;

/**
    * @Title: 学相伴出品
    * @Description: 我们有一个学习网站：https://www.kuangstudy.com
    * @author 飞哥
    * @date 2021/11/10 21:28
*/
public interface IUserService extends IService<User> {

    default void logined(String nickname,String password){}
}
