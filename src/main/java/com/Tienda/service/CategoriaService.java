/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.service;


import com.Tienda.domain.Categoria;
import java.util.List;

//Es como un contrato,nosotros definimos que va a tener ese contrato 
public interface CategoriaService {
    //Metodo para consultar las categorias.El parametro define si se muestra solo las activas o todas
    //FIRMA DE UN METODO, es el modificador, el tipo de dato de retorno, el nombre del metodo y el identificador
    public List<Categoria>getCategorias(boolean activos);//getcategorias porque me va a devolver varios info
    
    //Se obtiene una categoria por su id
    public Categoria getCategoria(Categoria categoria);
    
    //Metodo publico, recibe un objeto de tipo categoria qu4e se llame categoria
    //Nos sirve para insertar y modificar
    //Insertar:Se inserta cuando el IdCategoria esta vacio (valor 0 o null}
    //Modificar;Se modifica cuando el IDcategoria no esta vacio
    public void save (Categoria categoria);
    
    //Se elimina un registro por su idCategoria
    
    public void delete(Categoria categoria);
}
