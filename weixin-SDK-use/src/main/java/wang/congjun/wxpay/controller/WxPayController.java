package wang.congjun.wxpay.controller;

import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wang.congjun.utils.ResultVOUtil;
import wang.congjun.vo.ResultVO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private String openId;

    @ResponseBody
    @RequestMapping(value = "wxpay1")
    public ResultVO pay1(HttpServletRequest request, String orderNo, String subject) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        Cookie[] cookies = request.getCookies();
        String openid = "";
        for (Cookie cookie : cookies){
            String name = cookie.getName();
            if(name!=null&&name.equals("openid")) openid=cookie.getValue();
        }
        try {
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody("主题");
            orderRequest.setOutTradeNo(UUID.randomUUID().toString().substring(0, 15));
            orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee("0.1"));//元转成分
            log.info("ip={}", ip);
            orderRequest.setOpenid(openid);
            log.info("【微信支付】openid={}",openid);
//            orderRequest.setOpenid("oDLCK0WDXMD2KtIdDpmbBpbcTkaQ");
            orderRequest.setSpbillCreateIp(ip);
            String format = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
            orderRequest.setTimeStart(format);
            log.info("【微信支付】NotifyUrl={},MchKey={}", wxPayService.getConfig().getNotifyUrl(), wxPayService.getConfig().getMchKey());
            orderRequest.setTimeExpire(DateFormatUtils.format(DateUtils.addHours(new Date(), 1), "yyyyMMddHHmmss"));
            return ResultVOUtil.success(wxPayService.createOrder(orderRequest));
        } catch (Exception e) {
            log.error("微信支付失败！订单号：{},原因:{}", orderNo, e.getMessage());
            e.printStackTrace();
            return ResultVOUtil.error("支付失败，请稍后重试！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "wxpay")
    public ResultVO pay(HttpServletRequest request, String orderNo, String subject,Map<String, Object> map) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid("oDLCK0WDXMD2KtIdDpmbBpbcTkaQ");
        payRequest.setOrderAmount(1.1);
        payRequest.setOrderId(UUID.randomUUID().toString().substring(0, 15));
        payRequest.setOrderName("【微信支付】测试");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        PayResponse pay = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付, response={}", pay);
        map.put("payResponse", pay);
        map.put("returnUrl", "");
        return ResultVOUtil.success(pay);
    }


    @RequestMapping("/pay/result")
    @ResponseBody
    public ResultVO result() {
        return ResultVOUtil.success();
    }

}
