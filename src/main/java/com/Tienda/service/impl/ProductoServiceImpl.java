 
package com.Tienda.service.impl;

import com.Tienda.dao.ProductoDao;
import com.Tienda.domain.Producto;
import com.Tienda.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProductoServiceImpl implements ProductoService {

    @Autowired //Anotacion que nos permite crear una instancia de un elemento
    ProductoDao productoDao;

    @Transactional(readOnly = true)//org.springframework.transaction.annotation.Transactional; ELEGIMOS LA DE SPRING
    public List<Producto> getProductos(boolean activos) {
        List<Producto> productos = productoDao.findAll();
        //productos= productoDao.findAll(); se encarga de traerme todos los registros
        //List<Producto> Los registros se van para esta variable

        if (activos) {
            productos.removeIf(x -> !x.isActivo());//De las productos vamos a hacer un removeIf, nos permite remover elementos
            //de esta lista que cumplan con la caracteristica
        }
        return productos;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
    // Lista de productos con precio entre ordendados por descripción ConsultaAmpliada
    //
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup) {
        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
    
    //llama al productoDao metodoJPQL, mandandole los parametros precioInf precioSub
      @Override
    @Transactional(readOnly=true)    
    public List<Producto> metodoJPQL(double precioInf, double precioSup) {
        return productoDao.metodoJPQL(precioInf, precioSup);
    }
    
     @Override
    @Transactional(readOnly=true)    
    public List<Producto> metodoNativo(double precioInf, double precioSup) {
        return productoDao.metodoNativo(precioInf, precioSup);
    }

}

