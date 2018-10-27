package com.wang.Controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HuangDanGeeker on 2018/10/27.
 */
@Controller
@ComponentScan
public class BasicController {

    @RequestMapping(path = "/hello", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String hello(){
        return "hello spring boot mvc";
    }

}
