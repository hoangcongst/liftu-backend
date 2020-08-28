package me.tektap.liftu.dao;

import me.tektap.liftu.entity.Post.Post;
import me.tektap.liftu.entity.Post.PostAtIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    <T> Page<T> findAllByStatus(byte status, Class<T> type,Pageable pageable);
}
