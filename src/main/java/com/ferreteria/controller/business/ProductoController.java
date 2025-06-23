// Ubicación: src/main/java/com/ferreteria/controller/business/ProductoController.java
package com.ferreteria.controller.business;

import com.ferreteria.entities.business.Producto;
import com.ferreteria.service.business.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/app/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Este endpoint ahora usa nuestra lógica de negocio.
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto) {
        Producto productoGuardado = productoService.registrarProducto(producto);
        return new ResponseEntity<>(productoGuardado, HttpStatus.CREATED);
    }

    // Aquí irían los demás métodos (GET, PUT, DELETE) que usarían el ProductoRepository directamente.
}