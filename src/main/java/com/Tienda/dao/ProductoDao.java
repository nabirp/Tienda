/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.dao;

import com.Tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Nabir
 */
public interface ProductoDao extends JpaRepository<Producto, Long>{
    //Ejemplo de método utilizando Métodos de Query
    //El between me permite buscar valores en un rango
public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);

//Ejemplo de método utilizando Consultas con JPQL
//metodoJPQL este nombre se pone como quieras
//Me devuelve una lista productos donde traiga todos los valores de producto que este entre superior inferio, ordenado de forma ascendente
// "SELECT a FROM Producto a= a significar el nombre de la tabla producto
    @Query(value="SELECT a FROM Producto a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    //Definimos el metodo,en los paretesis decimos ("precioInf")}= el valor que va a ir en double precioInf y asi con el otro
    //los nombre los definimos igual pero pueden cambiar
    public List<Producto> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);
    
    
    //Ejemplo de método utilizando Consultas con SQL nativo
    //usamos el asterisco
    @Query(nativeQuery=true,
            value="SELECT * FROM producto where producto.precio BETWEEN :precioInf AND :precioSup ORDER BY producto.descripcion ASC")
    public List<Producto> metodoNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup); 

    
}
