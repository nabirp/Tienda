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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nabir
 */
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired //Anotacion que nos permite crear una instancia de un elemento
    CategoriaDao categoriaDao;

    @Transactional(readOnly = true)//org.springframework.transaction.annotation.Transactional; ELEGIMOS LA DE SPRING
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

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

}
