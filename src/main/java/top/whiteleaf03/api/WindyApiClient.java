package top.whiteleaf03.api;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WhiteLeaf03
 */
public class WindyApiClient {
    private final String GATEWAY_HOST;
    private final String ACCESS_KEY;
    private final SignUtil signUtil;

    public WindyApiClient(String gatewayHost, String accessKey, String secretKey) {
        this.GATEWAY_HOST = gatewayHost;
        this.ACCESS_KEY = accessKey;
        this.signUtil = new SignUtil(secretKey);
    }

    public String invokeApiByGet(HashMap<String, Object> params, String url) {
        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        //签名
        String sign = signUtil.genSign(JSONUtil.toJsonStr(params), timestamp);

        //请求头
        Map<String, String> headers = new HashMap<String, String>(3) {{
            put("accessKey", ACCESS_KEY);
            put("timestamp", timestamp);
            put("sign", sign);
            put("params", JSONUtil.toJsonStr(params));
        }};
        String paramStr = MapUtil.join(params, "&", "=");
        String result = HttpRequest.get(GATEWAY_HOST + url + paramStr)
                .addHeaders(headers)
                .execute().body();
        return result;
    }

    public String invokeApiByPost(HashMap<String, Object> body, String url) {
        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        //请求体
        String bodyJson = JSONUtil.toJsonStr(body);

        //签名
        String sign = signUtil.genSign(bodyJson, timestamp);

        //请求头
        Map<String, String> headers = new HashMap<String, String>(3) {{
            put("accessKey", ACCESS_KEY);
            put("timestamp", timestamp);
            put("sign", sign);
            put("body", bodyJson);
        }};

        String result = HttpRequest.post(GATEWAY_HOST + url)
                .addHeaders(headers)
                .body(bodyJson)
                .execute().body();
        return result;
    }
}
