package com.carvajal.wishlist.repository;

import com.carvajal.wishlist.entity.ListaDeseos;
import com.carvajal.wishlist.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ListaDeseosRepository extends JpaRepository<ListaDeseos, Long> {
    List<ListaDeseos> findByCliente(Cliente cliente);
    Optional<ListaDeseos> findByClienteAndProductoId(Cliente cliente, Long productoId);
}