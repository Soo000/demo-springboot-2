package com.alisls.demo.springboot.swagger.conf;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableConfigurationProperties({SwaggerProperties.class})
@Slf4j
@AllArgsConstructor
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        log.info("swaggerProperties配置值 {}", swaggerProperties);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
        return new ApiInfoBuilder()
                // 页面标题
                .title(swaggerProperties.getTitle())
                // 版本号
                .version(swaggerProperties.getVersion())
                // 描述
                .description(swaggerProperties.getDescription())
                .build();
    }

}
