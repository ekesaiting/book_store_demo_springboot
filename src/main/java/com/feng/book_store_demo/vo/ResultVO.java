package com.feng.book_store_demo.vo;


import com.feng.book_store_demo.constant.enums.LoginAndValidateEnum;
import com.feng.book_store_demo.constant.enums.ResultEnum;
import com.feng.book_store_demo.exception.BaseException;
import lombok.Data;

/**
 * @Author shf
 * @Date 2020/5/31 21:56
 */
@Data
public class ResultVO<T>{
    private int code;
    private String msg;
    private T data;
    //如果请求正常执行，则正常返回
   public ResultVO(ResultEnum res, T data){
       this.code=res.getCode();
       this.msg=res.getMsg();
       this.data=data;
   }
   //登入失败或者参数绑定异常，返回错误原因
   public ResultVO(LoginAndValidateEnum e, T data){
       this.code=e.getCode();
       this.msg=e.getMsg();
       this.data=data;
   }
   //如果业务中出现异常，返回异常原因
   public ResultVO(BaseException e, T data){
       this.code=e.getCode();
       this.msg=e.getMsg();
       this.data=data;
   }
}
