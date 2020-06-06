package com.feng.book_store_demo.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/5/31 21:27
 */
@AllArgsConstructor
@Getter
public enum LoginAndValidateEnum {
    VALIDATE_FAILED(4002,"参数效验失败"),
    ERROR_USERNAME(4003,"用户名错误"),
    ERROR_PASSWORD(4004,"密码错误");
    private final int code;
    private final String msg;
}
