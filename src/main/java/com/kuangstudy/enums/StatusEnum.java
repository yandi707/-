package com.kuangstudy.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum StatusEnum {

    PRIMARY(1, "小学"), SECONDORY(2, "中学"), HIGH(3, "高中");

    StatusEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue//标记数据库存的值是code
    private final int code;
    private final String descp;
}