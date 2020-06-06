package com.feng.book_store_demo.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


/**
 * @Author shf
 * @Date 2020/5/31 23:57
 */
@Data
public class LoginForm {
    @NotNull
    @Length(min = 2,max = 10,message = "用户名长度应在2~10个字符之间")
    private String username;
    @NotNull
    @Length(min = 6,message = "密码不能低于6个字符")
    @Length(max = 20,message = "密码超出20个字符")
    private String password;
}
