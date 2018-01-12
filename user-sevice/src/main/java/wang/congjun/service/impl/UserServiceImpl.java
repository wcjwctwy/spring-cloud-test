package wang.congjun.service.impl;

import org.springframework.stereotype.Service;
import wang.congjun.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserById(Integer id) {
        return "find a user'id ==== "+id;
    }
}
