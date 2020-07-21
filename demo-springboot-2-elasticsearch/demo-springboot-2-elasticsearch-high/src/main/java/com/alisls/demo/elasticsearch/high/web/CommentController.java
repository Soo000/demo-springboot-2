package com.alisls.demo.elasticsearch.high.web;

import com.alisls.demo.elasticsearch.high.dto.CommentDTO;
import com.alisls.demo.elasticsearch.high.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 评论Comment管理
 * Elasticsearch父子文档中的子文档，父文档为Blog
 *
 * @author Ke Wang
 * @date 2020/7/17
 */
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public CommentDTO save(@RequestBody CommentDTO commentDTO) {
        try {
            return commentService.save(commentDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
