package wang.congjun.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class WxCheckController {

    @Autowired
    private WxMpInMemoryConfigStorage wxMpConfigStorage;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    public String check(@RequestParam String echostr) {
        return echostr;
    }

    @ResponseBody
    @RequestMapping("/sell")
    public String get(HttpServletRequest request) throws Exception {
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            log.info("消息签名不正确，说明不是公众平台发过来的消息");
            return "非法请求";
        }

        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            log.info(echostr);
            return echostr;
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");
        log.info("encrypt_type={}", encryptType);
        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            log.info("inMessage={}",inMessage);
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            log.info("明文传输的消息");
            log.info("outMessage={}",outMessage);
            return outMessage.toXml();
        }

        if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            log.info("是aes加密的消息");
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            return outMessage.toEncryptedXml(wxMpConfigStorage);
        }

        log.info("不可识别的加密类型");
        return "不可识别的加密类型";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
