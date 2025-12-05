package com.utez.catalogo_inventario.service;



import com.utez.catalogo_inventario.DTOS.CreateProductoDTO;
import com.utez.catalogo_inventario.model.Producto;
import com.utez.catalogo_inventario.repository.PrductoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductoService {

    private final PrductoRepository productoRepository;

    public ProductoService(PrductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Map<String, Object> registrarProducto(CreateProductoDTO dto) {
        Map<String, Object> respuesta = new HashMap<>();


        if (productoRepository.existsBySKU(dto.getSku())) {
            respuesta.put("mensaje", "El SKUID ya está registrado");
            respuesta.put("code", 400);
            return respuesta;
        }



        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setSKU(dto.getSku());

        productoRepository.save(producto);

        respuesta.put("mensaje", "Producto registrado correctamente");
        respuesta.put("code", 201);
        respuesta.put("producto", producto);
        return respuesta;
    }

    public Map<String, Object> getAllProductos() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Producto> productos = productoRepository.findAll();
        respuesta.put("productos", productos);
        respuesta.put("mensaje", "Consulta exitosa");
        respuesta.put("code", 200);
        return respuesta;
    }

    public Map<String, Object> getProductoById(Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> optProducto = productoRepository.findById(id);

        if (optProducto.isPresent()) {
            respuesta.put("producto", optProducto.get());
            respuesta.put("mensaje", "Producto si encontrado");
            respuesta.put("code", 200);
        } else {
            respuesta.put("mensaje", "No se encontró el producto con id " + id);
            respuesta.put("code", 404);
        }
        return respuesta;
    }

    public Map<String, Object> actualizarProducto(Integer id, CreateProductoDTO dto) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> optProducto = productoRepository.findById(id);

        if (optProducto.isPresent()) {
            Producto productoExistente = optProducto.get();


            productoExistente.setNombre(dto.getNombre());
            productoExistente.setPrecio(dto.getPrecio());
            productoExistente.setSKU(dto.getSku());

            productoRepository.save(productoExistente);

            respuesta.put("mensaje", "Producto actualizado correctamente");
            respuesta.put("code", 200);
            respuesta.put("producto", productoExistente);
        } else {
            respuesta.put("mensaje", "Producto no encontrado");
            respuesta.put("code", 404);
        }
        return respuesta;
    }

    public Map<String, Object> eliminarProducto(Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        Optional<Producto> optProducto = productoRepository.findById(id);

        if (optProducto.isPresent()) {
            productoRepository.delete(optProducto.get());
            respuesta.put("mensaje", "Producto eliminado correctamente");
            respuesta.put("code", 200);
        } else {
            respuesta.put("mensaje", "Producto no encontrado");
            respuesta.put("code", 404);
        }
        return respuesta;
    }
}