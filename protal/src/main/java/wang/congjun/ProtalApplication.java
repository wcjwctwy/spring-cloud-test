package wang.congjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@EnableFeignClients
//@EnableEurekaClient
//@ComponentScan(excludeFilters = {@ComponentScan.Filter({NotScan.class, NotScanMain.class})})
public class ProtalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProtalApplication.class,args);
    }
}
