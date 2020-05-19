package com.alisls.demo.springboot.swagger.conf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger配置类
 *
 * @author Ke Wang
 */
@ConfigurationProperties(prefix = "swagger.prop")
@Getter
@Setter
@ToString
public class SwaggerProperties {

    private String title;

    private String version;

    private String description;

    private String basePackage;

}
