/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Tienda.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nabir
 */
@Controller
public class IndexController {
    
    @RequestMapping("/url")
    public String page(Model model) {
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
        return "index";
    }
    
}
