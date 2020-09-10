package me.tektap.liftu.dao;

import me.tektap.liftu.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String userName);
    <T> Optional<T> findUserById(long id, Class<T> type);
}
