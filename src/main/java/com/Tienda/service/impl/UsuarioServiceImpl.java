/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.dao.RolDao;
import com.Tienda.dao.UsuarioDao;
import com.Tienda.domain.Rol;
import com.Tienda.domain.Usuario;
import com.Tienda.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private RolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.findByUsernameOrCorreo(username, correo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        usuario=usuarioDao.save(usuario);
        if (crearRolUser) {  //Si se está creando el usuario, se crea el rol por defecto "USER"
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setIdUsuario(usuario.getIdUsuario());
            rolDao.save(rol);
        }
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}

/*@Service("userDetailsService")
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
      /* session.setAttribute("usuarioImagen", usuario.getRutaImangen());/*Agregar la imagen*/
       
      /* var roles= new ArrayList<GrantedAuthority>();/*Creamos el objeto que ocupamos, LISTA DE ROLES QUE VA A TENER EL USUARIO*/
       
       /*Por cada elemento dentro de usuario.getRoles(el me devuleve los roles de BD)le va a asignar el nombre rol para poderlo utilizar 
       dentro del for, poder iterar con el y poder sacar los datos que ocupamos */
      /* for (Rol rol: usuario.getRoles()){
           roles.add(new SimpleGrantedAuthority(rol.getNombre()));//transformo los objetos de tipo rol a tipo GRANTEDAUTHORITY
       }
       
       return new User(usuario.getUsername(), usuario.getPassword(),roles);
   }*/
