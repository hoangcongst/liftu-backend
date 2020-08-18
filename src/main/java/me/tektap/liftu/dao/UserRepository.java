package me.tektap.liftu.dao;

import me.tektap.liftu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE username=?1")
    public User findUserByUsername(String userName);
}
