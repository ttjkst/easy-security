package org.easySecurity.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public abstract class  SecurityUtils {
    private static final Log logger = LogFactory.getLog(SecurityUtils.class);

    public static  <T> T  getBean(HttpSecurity builder, Class<T> clazz){
        T sharedObject = builder.getSharedObject(clazz);
        if(sharedObject==null){
            logger.debug("cant not find bean("+clazz.getName()+") in shareObject, try find it in applicationContext");
            return getBeanInApplicationContext(builder,clazz);
        }
        return sharedObject;
    }

    public static <T> T getBeanInApplicationContext(HttpSecurity builder, Class<T> clazz){
        ApplicationContext applicationContext = getApplicationContext(builder);
        ObjectProvider<T> beanProvider = applicationContext.getBeanProvider(clazz);
        return beanProvider.getIfAvailable(()->null);
    }

    public static ApplicationContext getApplicationContext(HttpSecurity builder){
        return builder.getSharedObject(ApplicationContext.class);
    }
}
