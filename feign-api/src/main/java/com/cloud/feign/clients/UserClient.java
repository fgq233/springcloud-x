package com.cloud.feign.clients;


import com.cloud.feign.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "userservice",path = "/user/feign")
public interface UserClient {

    @GetMapping("/{id}")
    @ResponseBody
    User test1(@PathVariable("id") Long id);

    @RequestMapping("/test2")
    @ResponseBody
    User test2(@RequestParam("id") Long id, @RequestParam("username") String username);

    @PostMapping("/test3")
    @ResponseBody
    User test3(@RequestBody User user);

    @PostMapping("/test4")
    @ResponseBody
    User test4(@RequestBody Map<String, User> map);

    @PostMapping("/test5")
    @ResponseBody
    List<User> test5(@RequestBody List<User> list);

}
