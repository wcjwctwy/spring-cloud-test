package wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import wang.congjun.annotation.NotScan;
import wang.congjun.annotation.NotScanMain;

@SpringBootApplication
@EnableFeignClients
@PropertySource("classpath:config/feign-clien-address.properties")
public class MoveServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoveServiceApplication.class,args);
    }
}
