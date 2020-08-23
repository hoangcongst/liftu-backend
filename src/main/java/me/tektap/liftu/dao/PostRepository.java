package me.tektap.liftu.dao;

import me.tektap.liftu.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {

}
