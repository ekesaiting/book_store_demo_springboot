package com.feng.book_store_demo.aop;

import com.feng.book_store_demo.constant.enums.LoginAndValidateEnum;
import com.feng.book_store_demo.constant.enums.ResultEnum;
import com.feng.book_store_demo.exception.BaseException;
import com.feng.book_store_demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author shf
 * @Date 2020/5/15 8:41
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    //拦截所有未显式抛出的异常
    @ExceptionHandler(Exception.class)
    public ResultVO<String> globalExceptionHandle(Exception e){
        log.error("【未知异常】{}",e.getMessage());
        return new ResultVO<>(ResultEnum.ERROR,e.getMessage());
    }
    //参数未通过jsr303效验规则
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        log.error("【参数效验异常】{}",error.getDefaultMessage());
        return new ResultVO<>(LoginAndValidateEnum.VALIDATE_FAILED,error.getDefaultMessage());
    }
    //自定义异常返回前端
    @ExceptionHandler(BaseException.class)
    public ResultVO<String> APIExceptionHandler(BaseException e){
        return new ResultVO<>(e,null);
    }

    //前端传入的数据格式有误，不能正常解析，例如需要int类型，而前端传入的Json中没有对应类型
    @ExceptionHandler(NumberFormatException.class)
    public ResultVO<String> numberFormatExceptionHandler(NumberFormatException e){
        log.error("【数据格式异常】{}",e.getMessage());
        return new ResultVO<>(LoginAndValidateEnum.VALIDATE_FAILED,e.getMessage());
    }
}
