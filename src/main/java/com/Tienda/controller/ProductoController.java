package com.Tienda.controller;

import com.Tienda.domain.Categoria;
import com.Tienda.domain.Producto;
import com.Tienda.service.CategoriaService;
import com.Tienda.service.ProductoService;
import com.Tienda.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */




@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    ProductoService productoService;//Esta trae todos los productos
    
    @Autowired
    CategoriaService categoriaService;//Lo traigo porque voy a necesitar saber las categorias
                                                    
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @GetMapping("/listado")
    public String listado (Model model){
    List<Producto> productos= productoService.getProductos(false);
    List<Categoria> categorias = categoriaService.getCategorias(true);
    model.addAttribute("productos",productos);
    model.addAttribute("categorias",categorias);
    model.addAttribute("totalProductos",productos.size());    
    
    return "/producto/listado";
   } 
   //Nos redirige a una vista
    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
//El PostMapping permite enviar datos en el cuerpo de la peticion, van los datos de la categoria 
    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "producto",
                            producto.getIdProducto()));
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";//redirect=redireccionamos a categoria/lsitado en listado
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = categoriaService.getProducto(producto);
        List<Categoria> categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias",categorias);
        model.addAttribute("producto", producto);
        return "/producto/modifica";
    }
    
}
 