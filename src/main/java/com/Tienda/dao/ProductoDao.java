/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.dao;

import com.Tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Nabir
 */
public interface ProductoDao extends JpaRepository<Producto, Long>{
    //Ejemplo de método utilizando Métodos de Query
    //El between me permite buscar valores en un rango
public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
}
