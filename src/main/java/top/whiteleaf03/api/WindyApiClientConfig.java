package top.whiteleaf03.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author WhiteLeaf03
 */
@Configuration
@ConfigurationProperties("windy.api.client")
@ComponentScan
@Data
public class WindyApiClientConfig {
    private static String gatewayAddress;
    private static String accessKey;
    private static String secretKey;

    @Bean
    public WindyApiClient windyApiClient(){
        return new WindyApiClient(WindyApiClientConfig.gatewayAddress, WindyApiClientConfig.accessKey, WindyApiClientConfig.secretKey);
    }
}
