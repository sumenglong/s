package com.sml.sn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@MapperScan("com.sml.sn")
public class SNApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args){
        SpringApplication.run(com.sml.sn.SNApplication.class, args);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/SN/**").
                addResourceLocations("classpath:/SN/","file:SN/");
    }

}
