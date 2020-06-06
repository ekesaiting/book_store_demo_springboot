package com.feng.book_store_demo.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feng.book_store_demo.constant.enums.ResultEnum;
import com.feng.book_store_demo.vo.ResultVO;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 通知类
 * 拦截所有controller返回的结果,如果已经是使用resultVO包装则不增强直接返回，
 * 否则进行包装生成Result返回前端
 * @Author shf
 * @Date 2020/5/31 23:13
 */
@RestControllerAdvice(basePackages = "com.feng.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
       //比较Class,不相等返回true
        return !returnType.getGenericParameterType().equals(ResultVO.class);
    }
    //只有上面的方法返回true下面的方法才会执行
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
       if (returnType.getGenericParameterType().equals(String.class)){
           //string类型的数据不能直接包装，需要进行特殊处理
           ObjectMapper objectMapper=new ObjectMapper();
           try{
               return objectMapper.writeValueAsString(new ResultVO<>(ResultEnum.SUCCESS,body));
           } catch (JsonProcessingException e) {
              throw new Exception("返回的String类型解析错误");
           }
       }
       return new ResultVO<>(ResultEnum.SUCCESS,body);
    }
}
