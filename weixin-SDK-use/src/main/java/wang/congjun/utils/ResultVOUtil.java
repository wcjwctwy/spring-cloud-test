package wang.congjun.utils;

import wang.congjun.vo.ResultVO;

public class ResultVOUtil {

    /**
     * 返回ResultVO对象 有消息
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResultVO build(Integer code, String msg, Object data) {

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 返回ResultVO对象 没有消息
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO build(Integer code, String msg) {
        return build(code,msg,null);
    }

    /**
     * 返回成功消息 有数据
     * @param data
     * @return
     */
    public static ResultVO success(Object data){
        return  build(0,"suucess",data);
    }


    /**
     * 返回成功 没有消息
     * @return
     */
    public static ResultVO success(){
        return  success(null);
    }


    /**
     * 返回成功消息 有数据
     * @param msg
     * @return
     */
    public static ResultVO error(String msg){
        return  build(1,msg);
    }
}
