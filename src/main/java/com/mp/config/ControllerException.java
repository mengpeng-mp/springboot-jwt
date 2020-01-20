package com.mp.config;

import com.mp.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ControllerException implements ErrorController {
    private final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object handlerError(HttpServletRequest request){
        Map<String, Object> attributesMap = getErrorAttributes(request, true);
        System.out.println(attributesMap.get("message"));
        String msg = null;
        if (attributesMap.get("message").toString().indexOf("JWT expired") != -1) {
            msg = "JWT已过期";
        } else if (attributesMap.get("message").toString().indexOf("JWT strings must contain exactly 2 period characters") != -1){
            msg = "JWT格式错误";
        }else if(attributesMap.get("message").toString().indexOf("No message available") != -1){
            msg = "参数值缺失";
        } else {
            msg = attributesMap.get("message").toString();
        }
        return JsonResult.toJSONObject(Integer.parseInt(attributesMap.get("status").toString()), msg, attributesMap.get("message"));
    }

    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace){
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }
}
