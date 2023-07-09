package top.whiteleaf03.api;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * @author WhiteLeaf03
 */
public class SignUtil {
    private final String secretKey;

    public SignUtil(String secretKey) {
        this.secretKey = secretKey;
    }

    public String genSign(HashMap<String, Object> params, String timestamp) {
        String parasStr = JSONUtil.toJsonStr(params);
        String sourceStr = timestamp + "." + parasStr + "." + secretKey;
        return DigestUtil.sha256Hex(sourceStr);
    }

    public String genSign(String params, String timestamp) {
        String sourceStr = timestamp + "." + params + "." + secretKey;
        return DigestUtil.sha256Hex(sourceStr);
    }
}
