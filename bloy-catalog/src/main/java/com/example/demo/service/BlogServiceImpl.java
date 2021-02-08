package com.example.demo.service;

import javax.transaction.Transactional;

import com.example.demo.domain.Blog;
import com.example.demo.domain.Comment;
import com.example.demo.domain.User;
import com.example.demo.domain.Vote;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * Blog 服务.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年4月7日
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /* (non-Javadoc)
     * @see com.waylau.spring.boot.blog.service.BlogService#saveBlog(com.waylau.spring.boot.blog.domain.Blog)
     */
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    /* (non-Javadoc)
     * @see com.waylau.spring.boot.blog.service.BlogService#removeBlog(java.lang.Long)
     */
    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }

    /* (non-Javadoc)
     * @see com.waylau.spring.boot.blog.service.BlogService#updateBlog(com.waylau.spring.boot.blog.domain.Blog)
     */
    @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    /* (non-Javadoc)
     * @see com.waylau.spring.boot.blog.service.BlogService#getBlogById(java.lang.Long)
     */
    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleLikeAndSort(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        blog.setReadSize(blog.getCommentSize() + 1);
        blogRepository.save(blog);
    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        Blog originalBlog = blogRepository.findById(blogId).orElse(null);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment(user, commentContent);
        originalBlog.addComment(comment);
        return blogRepository.save(originalBlog);
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {
        Blog originalBlog = blogRepository.findById(blogId).orElse(null);
        originalBlog.removeComment(commentId);
        blogRepository.save(originalBlog);
    }

    @Override
    public Blog createVote(Long blogId) {
        Blog originalBlog = blogRepository.findById(blogId).orElse(null);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vote vote = new Vote(user);
        boolean isExist = originalBlog.addVote(vote);
        if (isExist) {
            throw new IllegalArgumentException("该用户已经点过赞了");
        }
        return blogRepository.save(originalBlog);
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog originalBlog = blogRepository.findById(blogId).orElse(null);
        originalBlog.removeVote(voteId);
        blogRepository.save(originalBlog);
    }
}