package com.example.demo.repository;

import com.example.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Comment 仓库.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年4月7日
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
