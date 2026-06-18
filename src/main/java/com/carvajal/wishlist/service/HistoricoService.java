package com.carvajal.wishlist.service;

import com.carvajal.wishlist.dto.HistoricoResponseDTO;
import com.carvajal.wishlist.entity.HistoricoListaDeseos;
import com.carvajal.wishlist.repository.HistoricoListaDeseosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final HistoricoListaDeseosRepository historicoRepository;

    public List<HistoricoResponseDTO> listarHistorico() {
        List<HistoricoResponseDTO> lista = new ArrayList<>();
        List<HistoricoListaDeseos> historicos = historicoRepository.findAll();

        for (HistoricoListaDeseos historico : historicos) {
            HistoricoResponseDTO dto = new HistoricoResponseDTO();
            dto.setId(historico.getId());
            dto.setNombreProducto(historico.getProducto().getNombre());
            dto.setAccion(historico.getAccion().name());
            dto.setFechaAccion(historico.getFechaAccion());
            lista.add(dto);
        }
        return lista;
    }
}