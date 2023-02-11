package com.order.demo.controller;

import com.cloud.feign.clients.UserClient;
import com.cloud.feign.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @RequestMapping("/test1")
    @ResponseBody
    public User test1() {
        return userClient.test1(666L);
    }

    @RequestMapping("/test2")
    @ResponseBody
    public User test2() {
        return userClient.test2(666L, "fgq");
    }

    @RequestMapping("/test3")
    @ResponseBody
    public User test3() {
        User user = new User();
        user.setId(888L);
        user.setUsername("fgq");
        return userClient.test3(user);
    }

    @RequestMapping("/test4")
    @ResponseBody
    public User test4() {
        User user = new User();
        user.setId(888L);
        user.setUsername("fgq");
        Map<String, User> map = new HashMap<>();
        map.put("user", user);
        return userClient.test4(map);
    }

    @RequestMapping("/test5")
    @ResponseBody
    public List<User> test5() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            User user = new User();
            user.setId((long) i);
            user.setDesc("fgq" + i);
            list.add(user);
        }
        return userClient.test5(list);
    }
}
