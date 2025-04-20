package com.learn.security.formbased.controller;
import com.learn.security.formbased.dto.AuthUserDto;
import com.learn.security.formbased.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SecurityController {
    @Autowired
    private AuthUserService authUserService;
    @PostMapping("/register")
    public String register(@RequestBody AuthUserDto authUserDto)
    {
        authUserService.save(authUserDto);
        return "user registered successfully";
    }
    @GetMapping("/")
    public String greet()
    {
        return "Hi hello world";
    }
    @GetMapping("/hello")
    public String hello()
    {
        return "hello world";
    }

}
