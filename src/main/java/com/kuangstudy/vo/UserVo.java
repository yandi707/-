package com.kuangstudy.vo;

import lombok.Data;

/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/11/14 23:08.
 * Update Date Time:
 *
 * @see
 */
@Data
public class UserVo {

    // 账号
    private String account;
    // 密码
    private String password;
    // 验证码
    private String code;

}
