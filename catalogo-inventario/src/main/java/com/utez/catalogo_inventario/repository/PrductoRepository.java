package com.utez.catalogo_inventario.repository;

import com.utez.catalogo_inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrductoRepository extends JpaRepository<Producto,Integer> {
    boolean existsBySKU(String sku);
}