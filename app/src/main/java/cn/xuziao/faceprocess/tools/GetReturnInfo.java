package cn.xuziao.faceprocess.tools;

public class GetReturnInfo {
    public static ReturnInfo get(String code) {
        for (ReturnInfo r : ReturnInfo.values()) {
            if (r.getCode() == Integer.parseInt(code)) {
                return r;
            }
        }
        return ReturnInfo.OTHERS;
    }
}
