package wang.congjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.congjun.feign.UserServiceFeignClient;

import javax.annotation.Resource;

@RestController
public class MovieController {

    @Autowired
    UserServiceFeignClient userService;

    @RequestMapping("/movie/user/{id}")
    public String getUser(@PathVariable Integer id){
        return userService.getUserById(id);
    }

}
