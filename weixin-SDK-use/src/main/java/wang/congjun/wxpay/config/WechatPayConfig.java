package wang.congjun.wxpay.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by 廖师兄
 * 2017-07-04 01:05
 */
@Component
public class WechatPayConfig {


    @Bean
    public BestPayServiceImpl bestPayService(WxPayH5Config wxPayH5Config) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }


    @Bean
    @ConfigurationProperties(prefix = "wechat.pay")
    public WxPayH5Config wxPayH5Config() {
        return new WxPayH5Config();
    }
}
