package com.carvajal.wishlist.repository;

import com.carvajal.wishlist.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}