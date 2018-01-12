package wang.congjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import wang.congjun.annotation.NotScan;
import wang.congjun.annotation.NotScanMain;
@NotScanMain
@SpringBootApplication
@EnableFeignClients
@PropertySource("classpath:config/feign-clien-address.properties")
//@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = NotScan.class)})

public class MoveServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoveServiceApplication.class,args);
    }
}
