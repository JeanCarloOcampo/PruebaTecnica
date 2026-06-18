package com.carvajal.wishlist.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoricoResponseDTO {
    private Long id;
    private String nombreProducto;
    private String accion;
    private LocalDateTime fechaAccion;
}