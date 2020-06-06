package com.feng.book_store_demo.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author shf
 * @Date 2020/5/31 21:43
 */

@Getter
@Setter
public abstract class BaseException extends RuntimeException {
    private  int code;
    private  String msg;

}
