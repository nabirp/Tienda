/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.service;


import com.Tienda.domain.Producto;
import java.util.List;

//Es como un contrato,nosotros definimos que va a tener ese contrato 
public interface ProductoService {
    //Metodo para consultar las productos.El parametro define si se muestra solo las activas o todas
    //FIRMA DE UN METODO, es el modificador, el tipo de dato de retorno, el nombre del metodo y el identificador
    public List<Producto>getProductos(boolean activos);//getproductos porque me va a devolver varios info
    
    //Se obtiene una producto por su id
    public Producto getProducto(Producto producto);
    
    //Metodo publico, recibe un objeto de tipo producto qu4e se llame producto
    //Nos sirve para insertar y modificar
    //Insertar:Se inserta cuando el IdProducto esta vacio (valor 0 o null}
    //Modificar;Se modifica cuando el IDproducto no esta vacio
    public void save (Producto producto);
    
    //Se elimina un registro por su idProducto
    
    public void delete(Producto producto);
}
