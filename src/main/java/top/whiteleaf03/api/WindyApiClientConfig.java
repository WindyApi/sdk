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
    private String gatewayAddress;
    private String accessKey;
    private String secretKey;

    @Bean
    public WindyApiClient windyApiClient(){
        return new WindyApiClient(gatewayAddress, accessKey, secretKey);
    }
}
