package com.carvajal.wishlist.service;

import com.carvajal.wishlist.dto.*;
import com.carvajal.wishlist.entity.*;
import com.carvajal.wishlist.entity.HistoricoListaDeseos.AccionHistorico;
import com.carvajal.wishlist.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListaDeseosService {

    private final ListaDeseosRepository listaDeseosRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final HistoricoListaDeseosRepository historicoRepository;

    // Agregar producto a la lista de deseos
    public MessageResponseDTO agregarProducto(ListaDeseosRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Cliente> clienteOpt = clienteRepository.findById(request.getClienteId());
        if (clienteOpt.isEmpty()) {
            response.setMessage("Cliente no encontrado");
            return response;
        }

        Optional<Producto> productoOpt = productoRepository.findById(request.getProductoId());
        if (productoOpt.isEmpty()) {
            response.setMessage("Producto no encontrado");
            return response;
        }

        Cliente cliente = clienteOpt.get();
        Producto producto = productoOpt.get();

        // Validar si ya existe ese producto en la lista del cliente
        Optional<ListaDeseos> existente = listaDeseosRepository
                .findByClienteAndProductoId(cliente, producto.getId());
        if (existente.isPresent()) {
            response.setMessage("Este producto ya está en la lista de deseos");
            return response;
        }

        ListaDeseos listaDeseos = new ListaDeseos();
        listaDeseos.setCliente(cliente);
        listaDeseos.setProducto(producto);
        listaDeseos.setCantidad(request.getCantidad());
        listaDeseos.setFechaAgregado(LocalDateTime.now());
        listaDeseosRepository.save(listaDeseos);

        // Registrar en el histórico
        registrarHistorico(cliente, producto, AccionHistorico.AGREGADO);

        response.setMessage("Producto agregado a la lista de deseos");
        return response;
    }

    // Listar productos de la lista de deseos de un cliente
    public List<ListaDeseosResponseDTO> listarPorCliente(Long clienteId) {
        List<ListaDeseosResponseDTO> lista = new ArrayList<>();

        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isEmpty()) {
            return lista;
        }

        List<ListaDeseos> listaDeseos = listaDeseosRepository.findByCliente(clienteOpt.get());

        for (ListaDeseos item : listaDeseos) {
            lista.add(mapearListaDeseos(item));
        }
        return lista;
    }

    // Actualizar cantidad de un producto en la lista
    public MessageResponseDTO actualizarCantidad(Long id, Integer nuevaCantidad) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<ListaDeseos> itemOpt = listaDeseosRepository.findById(id);
        if (itemOpt.isEmpty()) {
            response.setMessage("Item de la lista de deseos no encontrado");
            return response;
        }

        ListaDeseos item = itemOpt.get();
        item.setCantidad(nuevaCantidad);
        listaDeseosRepository.save(item);

        registrarHistorico(item.getCliente(), item.getProducto(), AccionHistorico.ACTUALIZADO);

        response.setMessage("Cantidad actualizada correctamente");
        return response;
    }

    // Eliminar producto de la lista de deseos
    public MessageResponseDTO eliminarProducto(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<ListaDeseos> itemOpt = listaDeseosRepository.findById(id);
        if (itemOpt.isEmpty()) {
            response.setMessage("Item de la lista de deseos no encontrado");
            return response;
        }

        ListaDeseos item = itemOpt.get();

        registrarHistorico(item.getCliente(), item.getProducto(), AccionHistorico.ELIMINADO);

        listaDeseosRepository.deleteById(id);
        response.setMessage("Producto eliminado de la lista de deseos");
        return response;
    }

    // Método privado para registrar en el histórico
    private void registrarHistorico(Cliente cliente, Producto producto, AccionHistorico accion) {
        HistoricoListaDeseos historico = new HistoricoListaDeseos();
        historico.setCliente(cliente);
        historico.setProducto(producto);
        historico.setAccion(accion);
        historico.setFechaAccion(LocalDateTime.now());
        historicoRepository.save(historico);
    }

    // Mapea la lista de deseos a DTO, incluyendo la validación de stock
    private ListaDeseosResponseDTO mapearListaDeseos(ListaDeseos item) {
        ListaDeseosResponseDTO dto = new ListaDeseosResponseDTO();
        dto.setId(item.getId());
        dto.setNombreProducto(item.getProducto().getNombre());
        dto.setPrecio(item.getProducto().getPrecio());
        dto.setCantidad(item.getCantidad());
        dto.setStockDisponible(item.getProducto().getStock());
        dto.setFechaAgregado(item.getFechaAgregado());

        // Regla de negocio: notificar si el producto ya no tiene stock suficiente
        boolean disponible = item.getProducto().getStock() >= item.getCantidad();
        dto.setDisponible(disponible);

        return dto;
    }
}