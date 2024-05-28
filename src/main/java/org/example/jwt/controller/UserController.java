package org.example.jwt.controller;


import org.example.jwt.entity.User;
import org.example.jwt.mapper.UserMapper;
import org.example.jwt.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/info")
    public String query(String token) {


        if(stringRedisTemplate.opsForValue().get(token)!=null){
            String email = TokenUtils.getClaimsByToken(token).getSubject();
            return "邮箱为："+email;
        }else {
            return "error";
        }

    }
    @PostMapping("/login")
    public String save(@RequestBody User user) {
        List<User> list=userMapper.query(user);
        if(list.size()==1) {
            System.out.println(user.getEmail());
            String token = TokenUtils.getToken(user.getEmail());
            System.out.println(token);
            stringRedisTemplate.opsForValue().set(token, user.getEmail());
            System.out.println(stringRedisTemplate.opsForValue().get(token));
            return token;
        }
        else{
            return "fail";
        }

    }
}
