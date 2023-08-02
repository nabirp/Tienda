/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.dao.UsuarioDao;
import com.Tienda.domain.Rol;
import com.Tienda.domain.Usuario;
import com.Tienda.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{
    
   @Autowired
   private UsuarioDao usuarioDao;
   
   @Autowired
   private HttpSession session;
   
   @Override
   @Transactional (readOnly=true)
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       //Busca el usuario en la base de datos
       Usuario usuario= usuarioDao.findByUsername (username);

       //Si el usuario no existe lanza la excepcion
       if(usuario==null){
           throw new UsernameNotFoundException(username);
       }
       
       session.removeAttribute("usuarioImagen");/*Removerlo en caso de que el atributo por si existe y luego agregarlo al usuario nuevo*/
       session.setAttribute("usuarioImagen", usuario.getRutaImangen());/*Agregar la imagen*/
       
       var roles= new ArrayList<GrantedAuthority>();/*Creamos el objeto que ocupamos, LISTA DE ROLES QUE VA A TENER EL USUARIO*/
       
       /*Por cada elemento dentro de usuario.getRoles(el me devuleve los roles de BD)le va a asignar el nombre rol para poderlo utilizar 
       dentro del for, poder iterar con el y poder sacar los datos que ocupamos */
       for (Rol rol: usuario.getRoles()){
           roles.add(new SimpleGrantedAuthority(rol.getNombre()));//transformo los objetos de tipo rol a tipo GRANTEDAUTHORITY
       }
       
       return new User(usuario.getUsername(), usuario.getPassword(),roles);
   }

    @Override
    public UserDetails LOADuSERbYuSERNAME(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
