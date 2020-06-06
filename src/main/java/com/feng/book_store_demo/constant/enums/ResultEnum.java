package com.feng.book_store_demo.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/5/31 21:16
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(2000,"执行成功"),
    SAVE(2001,"添加成功"),
    DELETE(2002,"删除成功"),
    UPDATE(2003,"修改成功"),


    FAILED(4000,"响应失败"),
    ERROR(4001,"未知错误");

    private final int code;
    private final String msg;
}
