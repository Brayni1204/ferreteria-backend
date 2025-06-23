package com.ferreteria.controller.business;

import com.ferreteria.entities.business.Cliente;
import com.ferreteria.repository.business.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')") // Admin y Cajero pueden crear clientes
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        // Aquí iría la lógica para validar DNI duplicado, similar a la de Usuario
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')") // Ambos roles pueden ver los clientes
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }
}