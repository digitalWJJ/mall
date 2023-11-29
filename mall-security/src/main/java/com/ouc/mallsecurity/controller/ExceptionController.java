package com.ouc.mallsecurity.controller;

import com.ouc.mallcommon.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    /**
     * 异常处理 controller request url
     *
     */

    @RequestMapping("error/throw")
    public void handleException(HttpServletRequest request){
        throw (ServiceException) request.getAttribute("filterError");
    }

}
