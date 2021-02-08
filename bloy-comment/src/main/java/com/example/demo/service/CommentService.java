package com.example.demo.service;

import com.example.demo.domain.Comment;

public interface CommentService {
    /*
    根据id获取comment
     */
    Comment getCommentById(Long id);

    /*
    删除评论
     */
    void removeComment(Long id);
}
