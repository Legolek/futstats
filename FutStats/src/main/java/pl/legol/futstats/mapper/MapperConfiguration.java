package pl.legol.futstats.mapper;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public DozerBeanMapperFactoryBean dozerMapperFactoryBean() {
        return new DozerBeanMapperFactoryBean();
    }
}
