/*
// Ubicación: src/main/java/com/ferreteria/config/TenantInterceptor.java
package com.ferreteria.config;

import com.ferreteria.tenant.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Estrategia 1: Leer desde una cabecera
        String tenantId = request.getHeader("X-Tenant-ID");
        if (tenantId != null && !tenantId.isEmpty()) {
            logger.info("Inquilino identificado por cabecera X-Tenant-ID: {}", tenantId);
            TenantContext.setCurrentTenant(tenantId);
            return true; // Devuelve true para continuar
        }
        // --- FIN DE LA MODIFICACIÓN ---


        // Estrategia 2: Fallback a subdominio (Para producción)
        String serverName = request.getServerName(); // ej: "compania-chavez.ferreteria.techinnovats.com"
        String[] parts = serverName.split("\\.");

        if (parts.length >= 3) {
            tenantId = parts[0];

            if (!tenantId.equalsIgnoreCase("www")) {
                logger.info("Inquilino identificado por subdominio: {}", tenantId);
                TenantContext.setCurrentTenant(tenantId);
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        TenantContext.clear();
    }
}*/
