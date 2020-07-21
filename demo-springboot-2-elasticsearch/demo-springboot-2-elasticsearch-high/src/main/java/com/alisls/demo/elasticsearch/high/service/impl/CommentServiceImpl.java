package com.alisls.demo.elasticsearch.high.service.impl;

import com.alisls.demo.elasticsearch.high.dto.BlogDTO;
import com.alisls.demo.elasticsearch.high.dto.CommentDTO;
import com.alisls.demo.elasticsearch.high.service.BlogService;
import com.alisls.demo.elasticsearch.high.service.CommentService;
import com.alisls.demo.elasticsearch.high.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 评论服务
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
@Service("commentService")
@AllArgsConstructor
public class CommentServiceImpl extends BusiServiceImpl implements CommentService {

    private final String BLOG_COMMENTS_INDEX = "my_blogs";

    private final DocumentService documentService;

    @Override
    public CommentDTO save(CommentDTO commentDTO) throws IOException {
        String id = commentDTO.getId().toString();
        String commentJson = objectToJson(commentDTO);
        documentService.saveDocument(BLOG_COMMENTS_INDEX, id, commentJson);
        return commentDTO;
    }

}
