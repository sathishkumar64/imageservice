package com.angadi.image.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.angadi.image.api.exception.CustomHttpErrorResponse;

/**
 * @author satvasu
 *
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(PATH)
    @ResponseBody
    public CustomHttpErrorResponse error(WebRequest request, HttpServletResponse response) {
        return new CustomHttpErrorResponse(response.getStatus(), getErrorAttributes(request));
    }

    public void setErrorAttributes(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(WebRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.putAll(this.errorAttributes.getErrorAttributes(request, false));
        return map;
    }
}
