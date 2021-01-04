package com.yangs.blog.utils;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author shuai.yang
 */
public class DozerUtils implements ApplicationContextAware {
    private static Mapper mapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        mapper = applicationContext.getBean(Mapper.class);
    }

    public static <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        if (source == null) {
            return null;
        }
        return mapper.map(source, destinationClass);
    }
}
