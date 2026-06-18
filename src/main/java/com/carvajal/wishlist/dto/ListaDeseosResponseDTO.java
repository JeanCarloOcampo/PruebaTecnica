package com.carvajal.wishlist.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ListaDeseosResponseDTO {
    private Long id;
    private String nombreProducto;
    private Double precio;
    private Integer cantidad;
    private Integer stockDisponible;
    private Boolean disponible;
    private LocalDateTime fechaAgregado;
}