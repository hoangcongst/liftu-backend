package me.tektap.liftu.dao;

import me.tektap.liftu.entity.Post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    <T> Page<T> findAllByStatus(byte status, Class<T> type,Pageable pageable);
}
