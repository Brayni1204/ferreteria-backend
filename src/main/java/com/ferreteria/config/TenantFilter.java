package com.ferreteria.config;

import com.ferreteria.tenant.TenantContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1) // Le damos la máxima prioridad para que se ejecute primero.
@Slf4j
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Solo procesamos si la ruta es de la app o de auth
        if (request.getRequestURI().startsWith("/api/app/") || request.getRequestURI().startsWith("/api/auth/")) {
            String tenantId = request.getHeader("X-Tenant-ID");

            if (tenantId == null || tenantId.isEmpty()) {
                // Si la ruta requiere un inquilino y no se proporciona, denegar acceso.
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("La cabecera X-Tenant-ID es requerida para esta peticion.");
                return;
            }

            try {
                log.info("TenantFilter: Estableciendo contexto para el inquilino: {}", tenantId);
                TenantContext.setCurrentTenant(tenantId);
                filterChain.doFilter(request, response);
            } finally {
                // Es crucial limpiar el contexto después de que la petición se haya procesado
                log.info("TenantFilter: Limpiando contexto del inquilino: {}", tenantId);
                TenantContext.clear();
            }
        } else {
            // Para otras rutas (como /api/superadmin), simplemente continuamos.
            filterChain.doFilter(request, response);
        }
    }
}