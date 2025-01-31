package com.proyecto_inventario.service;

import com.proyecto_inventario.dto.ProductoDto;
import com.proyecto_inventario.model.Producto;
import com.proyecto_inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDto> obtenerTodos() {
        return productoRepository.findAll().stream()
                .map(producto -> new ProductoDto(producto.getId(), producto.getNombre(), producto.getCantidad()))
                .collect(Collectors.toList());
    }

    public void crearProducto(ProductoDto productoDto) {
        Producto producto = new Producto();
        producto.setNombre(productoDto.getNombre());
        producto.setCantidad(productoDto.getCantidad());
        productoRepository.save(producto);
    }
}

