package com.carvajal.wishlist.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_lista_deseos")
@Data
public class HistoricoListaDeseos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    private AccionHistorico accion;

    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

    public enum AccionHistorico {
        AGREGADO, ELIMINADO, ACTUALIZADO
    }
}