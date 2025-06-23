// Ubicación: src/main/java/com/ferreteria/controller/business/UsuarioController.java
package com.ferreteria.controller.business;

import com.ferreteria.entities.business.Usuario;
import com.ferreteria.service.business.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
        // Ojo: En una aplicación real, nunca devolveríamos la contraseña, ni siquiera hasheada.
        // Se crea un DTO (Data Transfer Object) para filtrar qué se devuelve.
        usuarioCreado.setPassword(null); // Solución rápida para no exponer la contraseña.
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }
}