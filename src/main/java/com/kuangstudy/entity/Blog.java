package com.kuangstudy.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.kuangstudy.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
@TableName("kss_blog")
public class Blog implements java.io.Serializable {
    // 主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 内容标题
    private String title;
    // 分类内容
    private String content;
    // 分类id
    private Integer categoryId;
    // 分类名称
    private String categoryTitle;
    // 内容封面
    private String img;
    // 添加用户
    private Integer userId;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    // 发布状态 0未发布  1发布
    private StatusEnum status;
    // 删除状态 0未删除 1删除
    @TableLogic(value = "0", delval = "1")
    private Integer isDelete;
}
