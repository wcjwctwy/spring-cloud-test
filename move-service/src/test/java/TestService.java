

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.Test;
import wang.MoveServiceApplication;


//@SpringBootTest(classes = MoveServiceApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoveServiceApplication.class)
public class TestService
//        extends AbstractTestNGSpringContextTests
{

    @Autowired
    String name;

    @Test
    public void get(){
        System.out.println(name);
    }
}
