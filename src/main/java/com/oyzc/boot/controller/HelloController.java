package com.oyzc.boot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/car/{id}/owner/{name}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("name") String name,
                                      @PathVariable Map<String,String> pv) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("pv",pv);
        return map;
    }

    @GetMapping("/header")
    public Map<String, Object> getHeader(@RequestHeader("User-Agent") String userAgent,
                                         @RequestHeader Map<String,String> headers) {
        Map<String,Object> map = new HashMap<>();
        map.put("User-Agent",userAgent);
        map.put("headers",headers);
        return map;
    }

    @GetMapping("/param")
    public Map<String, Object> getParam(@RequestParam("age") Integer age,
                                        @RequestParam("hobby") List<String> hobby,
                                        @RequestParam Map<String,String> params) {
        Map<String,Object> map = new HashMap<>();
        map.put("age",age);
        map.put("hobby",hobby);
        map.put("params",params);
        return map;
    }

    @GetMapping("/cookie")
    public Map<String, Object> getParam(@CookieValue("_ga") String _ga,
                                        @CookieValue("_ga") Cookie cookie) {
        Map<String,Object> map = new HashMap<>();
        map.put("_ga",_ga);
        map.put("cookie",cookie);
        return map;
    }

    @PostMapping("/save")
    public Map<String, Object> postMethod(@RequestBody String content) {
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }

    @PostMapping("/save1")
    public Map<String, Object> postMethod1(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return map;
    }

}
