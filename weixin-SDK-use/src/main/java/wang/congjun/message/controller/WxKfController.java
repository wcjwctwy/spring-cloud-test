package wang.congjun.message.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WxKfController {

    @Autowired
    private WxMpService wxMpService;

    @ResponseBody
    @RequestMapping("/kf/send")
    public String send() throws Exception{
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setToUser("oqOXT0nwfrYsFgF9lY35ibPHzicQ");
        wxMpKefuMessage.setMsgType("text");
        wxMpKefuMessage.setContent("我是客服");
        wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
        return "success";
    }

}
