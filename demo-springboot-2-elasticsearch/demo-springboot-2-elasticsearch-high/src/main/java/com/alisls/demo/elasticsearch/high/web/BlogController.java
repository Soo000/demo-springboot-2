package com.alisls.demo.elasticsearch.high.web;

import com.alisls.demo.elasticsearch.high.dto.BlogDTO;
import com.alisls.demo.elasticsearch.high.dto.CommentDTO;
import com.alisls.demo.elasticsearch.high.dto.UserInfoDTO;
import com.alisls.demo.elasticsearch.high.service.BlogService;
import com.alisls.demo.elasticsearch.high.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 博客管理Blog
 * Elasticsearch父子文档中的父文档，子文档为评论Comment
 *
 * @author Ke Wang
 * @date 2020/7/17
 */
@RestController
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/save")
    public BlogDTO save(@RequestBody BlogDTO blogDTO) {
        try {
            return blogService.save(blogDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/list/{from}/{size}")
    public List<BlogDTO> list(@PathVariable int from,
                              @PathVariable int size,
                              @RequestParam String title) {
        try {
            return blogService.listByTitle(title, from, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据父文档中的标题查询，返回子文档信息
     * @param title
     * @return
     */
    @GetMapping("/getByParentTitle")
    public List<CommentDTO> getByParentTitle(@RequestParam String title) {
        try {
            return blogService.getByParentTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
