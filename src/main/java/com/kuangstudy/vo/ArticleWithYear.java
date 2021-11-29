package com.kuangstudy.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ArticleWithYear {
    // 年份
    private Integer year;
    // 该年份发布的文章列表
    private List<Map<String,Object>> articlesList;
}
