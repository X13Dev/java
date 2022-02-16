package com.example.server.dataObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户类
 */
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class User implements Serializable {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空！")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空！")
//    @Size(min = 8, message = "密码长度不能小于8！")
    private String password;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空！")
    private String email;

}