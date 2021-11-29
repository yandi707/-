package com.kuangstudy.service.archives;

import java.util.List;
import java.util.Map;

import com.kuangstudy.vo.ArticleWithYear;

/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/11/17 23:04.
 * Update Date Time:
 *
 * @see
 */
public interface IArchivesService {

    /**
     * 分页查询blog
     * @return
     */
    List<Map<String,Object>>  queryBlogsByYear();

    /**
     * Description: 查询内容信息并且分组
     * @date 2021/11/17
     * @author yykk
     * @param
     * @return
     * @throws Exception
     */
    List<Map<String, Object>>  queryBlogs();

    /**
     * Description: 查询内容信息并且分组
     * @date 2021/11/17
     * @author yykk
     * @param
     * @return
     * @throws Exception
     */
    List<ArticleWithYear> queryBlogs2();
}
