package wang.congjun.material;

import me.chanjar.weixin.mp.api.WxMpMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxMpMaterialController {

    @Autowired
    private WxMpMaterialService wxMpMaterialService;

    @ResponseBody
    @RequestMapping("/m/test")
    public Object test() throws Exception{
       return wxMpMaterialService.materialCount();
    }
}
