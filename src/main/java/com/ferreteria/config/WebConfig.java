/*
package com.ferreteria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TenantInterceptor tenantInterceptor;

    @Autowired
    public WebConfig(TenantInterceptor tenantInterceptor) {
        this.tenantInterceptor = tenantInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ANTES: solo interceptaba /api/app/**
        // registry.addInterceptor(tenantInterceptor).addPathPatterns("/api/app/**");

        // AHORA: Intercepta tanto las rutas de la app como las de autenticación.
        registry.addInterceptor(tenantInterceptor)
                .addPathPatterns("/api/app/**", "/api/auth/**");
    }
}*/
