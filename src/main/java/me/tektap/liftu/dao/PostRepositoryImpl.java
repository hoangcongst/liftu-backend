package me.tektap.liftu.dao;

import me.tektap.liftu.entity.Post.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {
    private EntityManager entityManager;

    public PostRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Post savePost(Post mPost) {
        entityManager.persist(mPost);
        return mPost;
    }
}
