package com.project.Testing.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorHandler implements ErrorController {

    //Custome error handling page.
    @RequestMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest){
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null && Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()){
            //When page not found!
            return "404";
        }
        //When some exception or error occurs.
        return "error";
    }
}
