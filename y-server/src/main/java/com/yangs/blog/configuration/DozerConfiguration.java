package com.yangs.blog.configuration;

import com.yangs.blog.utils.DozerUtils;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author shuai.yang
 */
@Configuration
public class DozerConfiguration {
    @Bean
    public DozerBeanMapperFactoryBean mapperFactoryBean() {
        DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
        Resource resource = new ClassPathResource("dozer/dozer-mapping.xml");
        if (resource.exists()) {
            factoryBean.setMappingFiles(new Resource[]{resource});
        }
        return factoryBean;
    }

    @Bean
    public DozerUtils dozerUtils() {
        return new DozerUtils();
    }
}
