package me.tektap.liftu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User implements UserDetails {
    public static final byte ACTIVE = 0;
    public static final byte BANNED = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @JsonIgnore
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "display_name", unique = true)
    private String display_name;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "email", unique = true)
    @JsonIgnore
    private String email;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "status")
    @JsonIgnore
    private byte status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date created_at;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updated_at;

    public User() {}

    /**
     * @param username
     * @param display_name
     * @param avatar
     * @param email
     * @param password
     * @param status
     */
    public User(String username, String display_name, String avatar,
                String email, String password, byte status) {
        this.username = username;
        this.display_name = display_name;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.status == User.ACTIVE;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
