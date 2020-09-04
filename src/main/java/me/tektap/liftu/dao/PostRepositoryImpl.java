package me.tektap.liftu.dao;

import me.tektap.liftu.entity.Post.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final EntityManager entityManager;

    public PostRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Post show(long postId) {
        return entityManager.find(Post.class, postId);
    }
}
