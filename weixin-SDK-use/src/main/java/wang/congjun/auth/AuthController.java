package wang.congjun.auth;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private String openId;

    @RequestMapping("/auth")
    public String auth(){
        String authorizationUrl = wxMpService.oauth2buildAuthorizationUrl("http://wwx.nat300.top/info", WxConsts.OAuth2Scope.SNSAPI_BASE, null);
        return "redirect:"+authorizationUrl;
    }


    @RequestMapping("/info")
    public String auth(@RequestParam("code") String code, HttpServletResponse response) throws Exception{
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
        Cookie cookie = new Cookie("openid",wxMpUser.getOpenId());
        cookie.setDomain("nat300.top");
        response.addCookie(cookie);
        return "redirect:/index";
    }


}
