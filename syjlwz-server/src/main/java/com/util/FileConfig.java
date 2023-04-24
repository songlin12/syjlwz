package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Value("${file.server.dir}")
    private String fileDir = "";
    @Value("${file.server.path}")
    private String uploadPath = "";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(uploadPath + "fileDir = " + fileDir);
        registry.addResourceHandler("/" + uploadPath + "/**").addResourceLocations("file:" + fileDir);
    }

    public String getFileDir() {
        return fileDir;
    }

    public String getUploadPath() {
        return uploadPath;
    }
}