package me.tektap.liftu.dao;

import me.tektap.liftu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String userName);
}
