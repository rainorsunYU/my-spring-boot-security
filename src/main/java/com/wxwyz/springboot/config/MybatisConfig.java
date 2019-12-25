package com.wxwyz.springboot.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }

//    //mybatis分页管理
//    @Bean
//    public PaginationInterceptor interceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        //设置请求页面大于最大页后，true调回首页，false继续请求
//        paginationInterceptor.setOverflow(true);
//        //参数-1表示不受限制
//        paginationInterceptor.setLimit(500);
//        return paginationInterceptor;
//    }


}
