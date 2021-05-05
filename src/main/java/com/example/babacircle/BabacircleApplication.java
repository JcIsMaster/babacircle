package com.example.babacircle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.babacircle.*.dao")
public class BabacircleApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(BabacircleApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BabacircleApplication.class, args);
    }

}
