package com.example.babacircle.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


/**
 * @author MQ
 * @date 2021/4/29 11:34
 */
@RestControllerAdvice
public class FileExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = MultipartException.class)
    Result arithmeticExceptionException(MultipartException e, HttpServletRequest request) {
        return new Result(-1,"上传单个文件大小只能上传30M","");
    }




}
