package me.tektap.liftu.config;

import me.tektap.liftu.entity.User.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> {
            if (SecurityContextHolder.getContext().getAuthentication() != null)
                return Optional.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            else return Optional.empty();
        };
    }
}