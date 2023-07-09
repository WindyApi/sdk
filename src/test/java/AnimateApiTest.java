import org.junit.Test;
import top.whiteleaf03.api.WindyApiClient;

import java.util.HashMap;

public class AnimateApiTest {
    private final static String gatewayAddress = "127.0.0.1:8080";
    private final static String accessKey = "caf63251d316940ac46faded09dfc0fecbc73811";
    private final static String secretKey = "5ddbc53401e54881c8bb0432e915fdf9d441efb2a425b541cca2e765280fb4f4";
    private final static WindyApiClient windyApiClient = new WindyApiClient(gatewayAddress, accessKey, secretKey);

    @Test
    public void avatarApiTest() {
        String result = windyApiClient.invokeApiByGet(new HashMap<>(), "/platform/api/interface/animate/avatar");
        System.out.println(result);
    }
}
