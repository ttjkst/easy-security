package org.easySecutity.client.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(EasySecurityClientAutoConfiguration.class)
public @interface EnableEasySecurityClientConfig {
}
