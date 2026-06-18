package com.carvajal.wishlist.controller;

import com.carvajal.wishlist.dto.*;
import com.carvajal.wishlist.service.ListaDeseosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista-deseos")
@RequiredArgsConstructor
public class ListaDeseosController {

    private final ListaDeseosService listaDeseosService;

    @PostMapping("/agregar")
    public ResponseEntity<MessageResponseDTO> agregarProducto(
            @Valid @RequestBody ListaDeseosRequestDTO request) {
        try {
            MessageResponseDTO response = listaDeseosService.agregarProducto(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            MessageResponseDTO error = new MessageResponseDTO();
            error.setMessage("Error al agregar el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/listar/{clienteId}")
    public ResponseEntity<List<ListaDeseosResponseDTO>> listarPorCliente(@PathVariable Long clienteId) {
        try {
            List<ListaDeseosResponseDTO> response = listaDeseosService.listarPorCliente(clienteId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MessageResponseDTO> actualizarCantidad(
            @PathVariable Long id,
            @RequestParam Integer cantidad) {
        try {
            MessageResponseDTO response = listaDeseosService.actualizarCantidad(id, cantidad);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            MessageResponseDTO error = new MessageResponseDTO();
            error.setMessage("Error al actualizar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MessageResponseDTO> eliminarProducto(@PathVariable Long id) {
        try {
            MessageResponseDTO response = listaDeseosService.eliminarProducto(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            MessageResponseDTO error = new MessageResponseDTO();
            error.setMessage("Error al eliminar: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}