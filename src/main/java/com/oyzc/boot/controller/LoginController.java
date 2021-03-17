package com.oyzc.boot.controller;

import com.oyzc.boot.bean.Account;
import com.oyzc.boot.bean.City;
import com.oyzc.boot.bean.User;
import com.oyzc.boot.exception.UserToManyException;
import com.oyzc.boot.service.impl.AccountServiceImpl;
import com.oyzc.boot.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private JdbcTemplate jdbcTemplate;

    private AccountServiceImpl accountService;

    private CityServiceImpl cityService;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setCityService(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @ResponseBody
    @GetMapping("/account")
    public Account getById(@RequestParam("id") Integer id) {
        return accountService.getById(id);
    }

    @ResponseBody
    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable("id") Long id) {
        return cityService.getById(id);
    }

    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city) {
        cityService.saveCity(city);
        return city;
    }

    @ResponseBody
    @GetMapping("/sql")
    public String query() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
        return aLong.toString();
    }

    @GetMapping(value = {"/","/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String home(User user, HttpSession session, Model model) {
        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            return "redirect:/home.html";
        }
        model.addAttribute("msg","账号密码错误");
        return "login";
    }

    @GetMapping("/home.html")
    public String homePage() {
        return "/pages/home";
    }

    @GetMapping("/test")
    public String test() {
        if (true) {
            throw new UserToManyException();
        }
        return "/pages/home";
    }
}
