/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.dao.CategoriaDao;
import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nabir
 */
//Las clases IMPL tiene una anotacion llamada SERVICE
//Lo que dice este service es que es una implementacion de la interfaz que vamos a hacer,tiene la logica de esa interfaz
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired //Anotacion que nos permite crear una instancia de un elemento
    CategoriaDao categoriaDao;

    @Override
    @Transactional (readOnly=true)//org.springframework.transaction.annotation.Transactional; ELEGIMOS LA DE SPRING
    public List<Categoria> getCategorias(boolean activos) {
        List<Categoria> categorias = categoriaDao.findAll();
        //categorias= categoriaDao.findAll(); se encarga de traerme todos los registros
        //List<Categoria> Los registros se van para esta variable

        if (activos) {
            categorias.removeIf(x -> !x.isActivo());//De las categorias vamos a hacer un removeIf, nos permite remover elementos
            //de esta lista que cumplan con la caracteristica
        }
        return categorias;
    }
}
