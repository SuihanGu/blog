package com.example.demo.service;

import com.example.demo.domain.Blog;
import com.example.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Blog 服务接口.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年4月7日
 */
public interface BlogService {
    /**
     * 保存Blog
     *
     * @param Blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 删除Blog
     *
     * @param id
     * @return
     */
    void removeBlog(Long id);

    /**
     * 更新Blog
     *
     * @param Blog
     * @return
     */
    Blog updateBlog(Blog blog);

    /**
     * 根据id获取Blog
     *
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * 根据用户名进行分页模糊查询（最新）
     *
     * @param user
     * @return
     */
    Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable);

    /**
     * 根据用户名进行分页模糊查询（最热）
     *
     * @param user
     * @return
     */
    Page<Blog> listBlogsByTitleLikeAndSort(User suser, String title, Pageable pageable);

    /**
     * 阅读量递增
     *
     * @param id
     */
    void readingIncrease(Long id);

    /*
    发表评论
     */
    Blog createComment(Long blogId, String commentContent);

    /*
    删除评论
     */
    void removeComment(Long blogId, Long commentId);
}
