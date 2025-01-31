package com.proyecto_inventario.controller;

import com.proyecto_inventario.dto.ProductoDto;
import com.proyecto_inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Endpoint para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerProductos() {
        List<ProductoDto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody ProductoDto productoDto) {
        productoService.crearProducto(productoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado con éxito");
    }
}

