package com.user.demo.controller;

import com.cloud.feign.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user/feign")
public class FeignController {


    @GetMapping("/{id}")
    @ResponseBody
    public User test1(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setDesc("@PathVariable");
        return user;
    }

    @RequestMapping("/test2")
    @ResponseBody
    public User test2(@RequestParam("id") Long id, @RequestParam("username") String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setDesc("@RequestParam");
        return user;
    }


    @PostMapping("/test3")
    @ResponseBody
    public User test3(@RequestBody User user) {
        user.setDesc("User实体");
        return user;
    }


    @PostMapping("/test4")
    @ResponseBody
    public User test4(@RequestBody Map<String, User> map) {
        User user = map.get("user");
        if (user != null) {
            user.setDesc("Map");
        }
        return user;
    }

    @PostMapping("/test5")
    @ResponseBody
    public List<User> test5(@RequestBody List<User> list) {
        for (User user : list) {
            user.setDesc("List");
        }
        return list;
    }
}
