package com.carvajal.wishlist.repository;

import com.carvajal.wishlist.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}