package com.im.port.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 오류 발생시에 대한 컨트롤러 입니다.
 * **/
@Controller
public class ExceptionController implements ErrorController   {
    /**
     * 오류를 처리합니다.
     * **/
    @RequestMapping(value = "/error")
    public String handleNoHandlerFoundException(HttpServletResponse response, HttpServletRequest request) {
        int status = response.getStatus();
		
        System.out.println(status);  //오류 상태
        System.out.println(request.getRequestURI());  //요청 주소
    	
        if (Objects.equals(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            // Map<String, Object> body = Map.of("error", "Not Found", "timestamp", System.currentTimeMillis());
            System.out.println("error 발생");
            return "index";
        }
        return "index";
    }
}