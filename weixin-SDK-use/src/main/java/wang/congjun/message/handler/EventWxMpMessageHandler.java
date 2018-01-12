package wang.congjun.message.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class EventWxMpMessageHandler implements WxMpMessageHandler {

    private static Map<String,String> dic = new HashMap<>();
   static {
        dic.put("哈哈","你好");
        dic.put("V1001_GOOD","你的支持就是我们的动力");
        dic.put("百度","点击<a href='http://www.baidu.com'>百度</a>冲浪吧！");
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        log.info("EventWxMpMessageHandler返回消息=====");
        String res = dic.get(wxMpXmlMessage.getEventKey());
        if(res==null)res="您的输入有误!";
        WxMpXmlOutTextMessage m
                = WxMpXmlOutMessage.TEXT().content(res).fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser()).build();
        return m;
    }
}
