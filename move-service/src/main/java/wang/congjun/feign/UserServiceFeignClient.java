package wang.congjun.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-service",url = "${user.service.feign}")
public interface UserServiceFeignClient {

    @RequestMapping(value="/user/{id}",method = RequestMethod.GET)
    String getUserById(@PathVariable("id") Integer id);

}
