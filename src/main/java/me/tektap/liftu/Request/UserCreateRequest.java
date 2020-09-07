package me.tektap.liftu.Request;

import lombok.Data;

import javax.servlet.annotation.HttpConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserCreateRequest implements Serializable {
    private static final long serialVersionUID = -5616176897013108345L;

    @NotNull
    @Size(min = 5, max = 20)
    private String username;
    @NotNull
    @Size(max = 255)
    @Email
    private String email;
    @NotNull
    @Size(min = 10, max = 50)
    private String display_name;
    @NotNull
    @Size(min = 8, max = 50)
    private String password;
    @Size(max = 255)
    @Pattern(regexp = "([a-z\\-_0-9\\/\\:\\.]*\\.(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF))", message = "Avatar must be image")
    private String avatar;
    private byte status;

    public UserCreateRequest(String username, String email, String display_name, String password, String avatar) {
        this.username = username;
        this.email = email;
        this.display_name = display_name;
        this.avatar = avatar;
        this.password = password;
    }
}
