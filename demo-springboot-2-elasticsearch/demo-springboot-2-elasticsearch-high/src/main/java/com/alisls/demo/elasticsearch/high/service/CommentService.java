package com.alisls.demo.elasticsearch.high.service;

import com.alisls.demo.elasticsearch.high.dto.CommentDTO;

import java.io.IOException;
import java.util.Map;

/**
 * 评论服务
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
public interface CommentService {

    /**
     * 保存评论文档
     * @param commentDTO
     * @return
     * @throws IOException
     */
    CommentDTO save(CommentDTO commentDTO) throws IOException;

}
