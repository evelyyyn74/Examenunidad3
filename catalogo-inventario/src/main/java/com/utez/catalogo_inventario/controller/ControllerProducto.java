package com.utez.catalogo_inventario.controller;

import com.utez.catalogo_inventario.DTOS.CreateProductoDTO;
import com.utez.catalogo_inventario.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "*")
public class  ControllerProducto {

    private final ProductoService productoService;

    public ControllerProducto(ProductoService productoService) {
        this.productoService = productoService;
    }
    @PostMapping("")
    public ResponseEntity<Object> registrarProducto(@RequestBody CreateProductoDTO dto) {
        Map<String, Object> respuesta = productoService.registrarProducto(dto);
        int code = (int) respuesta.get("code");
        return new ResponseEntity<>(respuesta,
                code == 201 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
        Map<String, Object> respuesta = productoService.getProductoById(id);
        int code = (int) respuesta.get("code");
        return new ResponseEntity<>(respuesta,
                code == 200 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable Integer id,
                                                     @RequestBody CreateProductoDTO dto) {
        Map<String, Object> respuesta = productoService.actualizarProducto(id, dto);
        int code = (int) respuesta.get("code");
        return new ResponseEntity<>(respuesta,
                code == 200 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }



}
