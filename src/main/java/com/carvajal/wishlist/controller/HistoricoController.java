package com.carvajal.wishlist.controller;

import com.carvajal.wishlist.dto.HistoricoResponseDTO;
import com.carvajal.wishlist.service.HistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoService historicoService;

    @GetMapping("/listar")
    public ResponseEntity<List<HistoricoResponseDTO>> listarHistorico() {
        try {
            List<HistoricoResponseDTO> response = historicoService.listarHistorico();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}