package shop.mtcoding.blogv2._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

// 사진 외부경로 표시하는 법
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/images/**")
            .addResourceLocations("file:"+"./images/")
            .setCachePeriod(10) // 10 (초)
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
    }


    }
