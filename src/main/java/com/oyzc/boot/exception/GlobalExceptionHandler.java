package com.oyzc.boot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理异常（数学运算异常，空指针异常）
     * 可以返回地址页，也可以返回ModelAndView
     * @return 视图
     */
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String handlerCalculationException(Exception e) {

        log.error("异常为",e);
        return "pages/home";
    }
}
