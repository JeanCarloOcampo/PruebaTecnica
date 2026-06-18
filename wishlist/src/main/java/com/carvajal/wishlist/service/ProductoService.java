package com.carvajal.wishlist.service;

import com.carvajal.wishlist.dto.*;
import com.carvajal.wishlist.entity.Producto;
import com.carvajal.wishlist.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<ProductoResponseDTO> listarProductos() {
        List<ProductoResponseDTO> lista = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();

        for (Producto producto : productos) {
            lista.add(mapearProducto(producto));
        }
        return lista;
    }

    private ProductoResponseDTO mapearProducto(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }
}