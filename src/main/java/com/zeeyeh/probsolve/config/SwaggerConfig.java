package com.zeeyeh.probsolve.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        security = {
                @SecurityRequirement(name = "Authorization")
        }
)
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        paramName = "Authorization"
)
public class SwaggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    private final static List<String> EXCLUDE_PATHS = List.of(
            "/sys/user/login",
            "/sys/user/register"
    );

    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((path, pathItem) -> {
                    if (EXCLUDE_PATHS.stream().anyMatch(path::equals)) {
                        return;
                    }
                    if (pathItem.getGet() != null) {
                        return;
                    }
                    pathItem.readOperations().forEach(operation -> {
                        operation.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
                    });
                });
            }
        };
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Probsolve")
                        .description("ProbSolve 后端接口文档")
                        .version("v1")
                        .contact(new Contact()
                                .name("Qinloren")
                                .email("2336248419@qq.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                        )
                );
    }
}
