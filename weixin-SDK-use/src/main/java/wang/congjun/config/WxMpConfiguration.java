package wang.congjun.config;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxMpConfiguration {

    @ConfigurationProperties(prefix = "wxmp")
    @Bean
    public WxMpInMemoryConfigStorage getWxMpInMemoryConfigStorage(){
        return new WxMpInMemoryConfigStorage();
    }

    @Bean
    @Autowired
    public WxMpService getWxMpService(WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
        return wxMpService;
    }




}
