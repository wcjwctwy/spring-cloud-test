package wang.congjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import org.testng.annotations.Test;


@Service
public class TestService
//        extends AbstractTestNGSpringContextTests
{

    @Autowired
    String name;


    public String get(){
        System.out.println(name);
        return name;
    }
}
