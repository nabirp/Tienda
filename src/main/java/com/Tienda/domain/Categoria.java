

package com.Tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

//ANOTACIONES DEBEN IR PEGADAS AL PUBLIC, A LA PAR SIN RENGLONES
//Una anotacion de Loonbok(dependencia que añedimos)
//Permite crear como un codigo escondido donde tenemos los get y set, to sting, de los metodos
@Data
//Esta es una entidad que se va a mapear con ...
@Entity
//Nombre de la tabla de la base de datos a la cual nos vamos a mapear en My SQL
//Tener cuidado con mayusculas y minusculas 
@Table(name = "categoria")
public class Categoria implements Serializable { //CONVIERTE EL OBJETO EN UN ARREGLO DE BITS

    private static final long serialVersionUID = 1L;
//Cuando vaya a crear el ID sepa como hacerlo
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name ="id_categoria")//este campo se comunica directamento con id_categoria
    private Long idCategoria;// Transformar en id_categoria, NO SE DEBEN PONER LOS GUION BAJO __
    private String descripcion;
    private String rutaImagen;
    private boolean activo;

    @OneToMany//De categorias a producto
    @JoinColumn(name = "id_categoria")
    private List<Producto> productos;

    //Constructor base
    public Categoria() {

    }
//Constructor sobre cargado,TODO MENOS EL ID

    public Categoria(String descripcion, String rutaImagen, boolean activo) {
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
}
