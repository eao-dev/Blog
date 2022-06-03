package com.blog.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:accessSettings.properties")
public class WebConfig implements WebMvcConfigurer {

}