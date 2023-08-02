/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


        
@Entity
@Data
@Table(name ="rol")
public class Rol implements Serializable {
   private static final long serialVersionUID = 1L;

    @Id //nota para decirle que es el id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_rol")
    private Long idRol;

    @NotEmpty
    private String nombre;

    @Column(name = "id_usuario")
    private Long idUsuario;
    }