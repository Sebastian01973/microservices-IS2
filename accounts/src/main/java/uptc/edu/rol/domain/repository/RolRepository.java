package uptc.edu.rol.domain.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import uptc.edu.rol.domain.models.Rol;

import java.util.List;


public interface RolRepository {

    Rol getRolById(String id);

    Rol saveRol(Rol rol);

    Rol deleteRol(Rol rol);

    Rol updateRol(Rol rol);

    List<Rol> getAllRoles();

}
