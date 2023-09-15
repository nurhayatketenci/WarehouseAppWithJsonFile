package com.warehousemanagement.task.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI baseOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Warehouse Management")
                        .version("1.0.0")
                        .description("OpenAPI documentation "));
    }
}
