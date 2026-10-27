package com.codeit.springbeanexampleV8;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/** InitializingBean/DisposableBean 방식은 Spring 인터페이스에 직접 의존합니다. 다른 두 방식과 비교하세요. */
@Component
public class InterfaceTestBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }
}
