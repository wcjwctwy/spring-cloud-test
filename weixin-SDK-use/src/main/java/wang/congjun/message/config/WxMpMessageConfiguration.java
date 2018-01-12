package wang.congjun.message.config;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import wang.congjun.message.handler.EventWxMpMessageHandler;
import wang.congjun.message.handler.TextWxMpMessageHandler;

@Component
public class WxMpMessageConfiguration {
    @Bean
    @Autowired
    public WxMpMessageRouter getWxMpMessageRouter(
            WxMpService wxMpService
            , TextWxMpMessageHandler handler
            , EventWxMpMessageHandler eventWxMpMessageHandler
    ) {
        WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
        wxMpMessageRouter
                .rule()
                .async(false)
                .msgType(WxConsts.XmlMsgType.TEXT)
//                .content("哈哈") // 拦截内容为“哈哈”的消息
                .handler(handler)
                .end()
                .rule()
                .async(false)
                .msgType(WxConsts.XmlMsgType.EVENT)
//                .content("哈哈") // 拦截内容为“哈哈”的消息
                .handler(eventWxMpMessageHandler)
                .end();
        return wxMpMessageRouter;
    }
}
