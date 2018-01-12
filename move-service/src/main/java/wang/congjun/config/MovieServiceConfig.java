package wang.congjun.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import wang.congjun.annotation.NotScan;

@Configuration
public class MovieServiceConfig {
    @Bean
    public String get(){
        return "HelloWorld";
    }
}
