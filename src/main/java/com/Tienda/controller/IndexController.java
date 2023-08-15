/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.dao.UsuarioDao;
import com.Tienda.domain.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @Autowired
    UsuarioDao usuarioDao;
    
    @RequestMapping("/url")
    public String page(Model model, HttpSession httpSession) {
 /*     //Obtener el usuario que es logeado 
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
        UserDetails user= null;
        
        if(principal instanceof UserDetails){
            user = (UserDetails) principal;
        }
        //Si el usuario no es nulo
        if (user != null){
            Usuario usuario = usuarioDao.findByUsername(user.getUsername());
            
            httpSession.setAttribute("Email", usuario.getCorreo());
        }*/ 
        return "index";
    }
    
}
