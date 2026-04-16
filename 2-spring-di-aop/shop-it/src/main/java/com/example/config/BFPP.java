package com.example.config;

import org.springframework.stereotype.Component;

// @Component
public class BFPP implements org.springframework.beans.factory.config.BeanFactoryPostProcessor {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger("shop-it-service");

    @Override
    public void postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory beanFactory)
            throws org.springframework.beans.BeansException {
        logger.info("BFPP: postProcessBeanFactory called");
        // String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        // logger.info("BFPP: Bean definitions count: {}", beanDefinitionNames.length);
        // for (String beanName : beanDefinitionNames) {
        //     logger.info("BFPP: Bean definition: {}", beanName);
        // }
    }
    
}
