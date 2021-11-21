package com.kuangstudy.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/11/11 17:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("kss_blog_category")
public class BlogCategory implements java.io.Serializable {

    @TableId(type = IdType.AUTO)
    // 主键
    private Integer id;
    // 分类标题
    private String categoryTitle;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    // 分类的发布状态 0 未发布 1 发布
    private Integer status;
    // 分类排序
    private Integer sorted;

}
