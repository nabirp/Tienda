/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.service.UsuarioService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    
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
           throw new UsernameNorFoundException(username);
       }
       
       session.removeAttribute("usuarioImagen");
       session.setAttribute("usuarioImagen", usuario.getRutaImagen());
       
       var roles= new ArrayList<GrantedAuthority>();
       
       for (Rol rol: usuario.getRoles()){
           roles.add(new SimpleGrantedAuthority(rol.getNombre()));
       }
       
       return new User(usuario.getUsername(), usuario.getPassword(),roles)
   }
}
