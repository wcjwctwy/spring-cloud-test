package wang.congjun.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import wang.MoveServiceApplication;

import static org.testng.Assert.*;

@SpringBootTest(classes = MoveServiceApplication.class)
public class TestServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    String name;

    @Test
    public void testGet() throws Exception {
        System.out.println(name);
    }

}