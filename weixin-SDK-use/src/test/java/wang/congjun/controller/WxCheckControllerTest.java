package wang.congjun.controller;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import wang.congjun.WxMpApplication;

import static org.testng.Assert.*;

@SpringBootTest(classes = WxMpApplication.class)
public class WxCheckControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WxMpService wxMpService;

    @Test
    public void testCheck() throws Exception {
        String aesKey = wxMpService.getWxMpConfigStorage().getAesKey();
        System.out.println(aesKey);
        Assert.assertNotNull(aesKey);
    }

}