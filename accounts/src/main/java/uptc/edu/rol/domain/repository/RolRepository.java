package uptc.edu.rol.domain.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import uptc.edu.rol.domain.models.Rol;

import java.util.List;
import java.util.Optional;


public interface RolRepository {

    Optional<Rol> getRolById(String id);

    Rol saveRol(Rol rol);

    boolean deleteRol(String id);

    Optional<Rol> updateRol(String id,Rol rol);

    List<Rol> getAllRoles();

}
