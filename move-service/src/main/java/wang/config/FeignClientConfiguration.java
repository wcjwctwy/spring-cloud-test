package wang.config;

import feign.Contract;
import org.springframework.context.annotation.Configuration;


//@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = NotScan.class)})
@Configuration
public class FeignClientConfiguration {
   public Contract getContract(){
       Contract.Default aDefault = new Contract.Default();
       return aDefault;

   }
}
