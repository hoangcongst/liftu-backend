package me.tektap.liftu.dao;

import me.tektap.liftu.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @RestResource(path="index")
    Page<Comment> findAllByPostIdAndStatusAndParentCommentIdOrderByIdDesc(long postId, byte status, long parentCommentId, Pageable pageable);
}
