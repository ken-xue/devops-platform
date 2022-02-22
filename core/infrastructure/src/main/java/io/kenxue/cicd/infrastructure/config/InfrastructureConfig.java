package io.kenxue.cicd.infrastructure.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


/**
 * 配置
 */
@Configuration
public class InfrastructureConfig {

    /**
     * mbp分页
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public CuratorFramework curatorFramework(){
        CuratorFramework framework = CuratorFrameworkFactory.builder()
                .connectString("116.198.160.30:2182")
                .retryPolicy(new BoundedExponentialBackoffRetry(5000,5000,5000))
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(15000)
                .build();
        framework.start();
        return framework;
    }

}