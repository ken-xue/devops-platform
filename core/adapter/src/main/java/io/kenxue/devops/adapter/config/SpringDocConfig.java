package io.kenxue.devops.adapter.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc API文档相关配置
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI mallTinyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("devops-platform")
                        .description("接口文档")
                        .version("v1.0.0")
                        .license(new License().name("MIT").url("https://github.com/ken-xue/devops-platform")))
                .externalDocs(new ExternalDocumentation()
                        .description("一套企业级开发、效能、运维平台。打通项目立项、研发、测试、发布、运维等流程，对软件项目开发形成闭环。")
                        .url("https://github.com/ken-xue/devops-platform"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("brand")
                .pathsToMatch("/brand/**")
                .packagesToScan("io.kenxue.devops.adapter.rest.*")
                .build();
    }
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .build();
    }
}
