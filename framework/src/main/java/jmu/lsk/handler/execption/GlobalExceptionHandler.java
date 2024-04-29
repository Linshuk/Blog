package jmu.lsk.handler.execption;


import jmu.lsk.domain.ResponseResult;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

//@ControllerAdvice //对controller层的增强
//@ResponseBody

//或者用下面一个注解代替上面的两个注解
@RestControllerAdvice

//使用Lombok提供的Slf4j注解，实现日志功能
@Slf4j

//全局异常处理。最终都会在这个类进行处理异常
public class GlobalExceptionHandler {

    //SystemException是我们写的类。用户登录的异常交给这里处理
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){

        //打印异常信息，方便我们追溯问题的原因。{}是占位符，具体值由e决定
        log.error("出现了异常! {}",e);

        //从异常对象中获取提示信息封装，然后返回。ResponseResult是我们写的类
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    //其它异常交给这里处理
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){

        //打印异常信息，方便我们追溯问题的原因。{}是占位符，具体值由e决定
        log.error("出现了异常! {}",e);

        //从异常对象中获取提示信息封装，然后返回。ResponseResult、AppHttpCodeEnum是我们写的类
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());//枚举值是500
    }

    //Validation的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult validationExceptionHandler(MethodArgumentNotValidException e){
        return 	ResponseResult.errorResult(AppHttpCodeEnum.ERROR_PARAM.getCode(), e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("; "))
        );
    }

}