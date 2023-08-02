
package com.Tienda.dao;

import com.Tienda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    //Este objeto va a devolver un usuario
    //Username es un String entonces eso nos debe devolver
    Usuario findByUsername(String username);
}
