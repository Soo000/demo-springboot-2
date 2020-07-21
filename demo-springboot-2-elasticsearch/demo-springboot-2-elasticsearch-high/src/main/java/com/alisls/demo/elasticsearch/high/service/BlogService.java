package com.alisls.demo.elasticsearch.high.service;

import com.alisls.demo.elasticsearch.high.dto.BlogDTO;
import com.alisls.demo.elasticsearch.high.dto.CommentDTO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 博客服务
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
public interface BlogService {

    /**
     * 保存博客文档
     * @param blogDTO
     * @return
     * @throws IOException
     */
    BlogDTO save(BlogDTO blogDTO) throws IOException;

    /**
     * 根据标题查询
     * @param title
     * @return
     * @throws IOException
     */
    List<BlogDTO> listByTitle(String title, int from, int size) throws IOException;

    /**
     * 对父文档进行查询，返回有这个父文档的子文档
     * @param title
     * @return
     */
    List<CommentDTO> getByParentTitle(String title) throws IOException;
}
