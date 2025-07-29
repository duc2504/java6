package com.example.java6;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API - Lab 3.1")
                        .version("1.0.0")
                        .description("API quản lý Course, Student, Enrollment Đỗ Văn Quý - java6 - SD20102")
                        .contact(new Contact()
                                .name("PhongTT35")
                                .email("phongtt35@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }



}
