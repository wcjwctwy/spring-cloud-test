package wang.congjun.material.config;

import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMaterialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WxMpMaterialConfiguration {

    @Bean
    @Autowired
    public WxMpMaterialService wxMpMaterialService(WxMpService wxMpService){
        WxMpMaterialServiceImpl wxMpMaterialService = new WxMpMaterialServiceImpl(wxMpService);
        return wxMpMaterialService;
    }

}
