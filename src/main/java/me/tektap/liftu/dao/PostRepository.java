package me.tektap.liftu.dao;

import me.tektap.liftu.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    @Query("FROM posts WHERE status=?1")
    public Page<Post> findByStatus(int status, Pageable pageable);
}
