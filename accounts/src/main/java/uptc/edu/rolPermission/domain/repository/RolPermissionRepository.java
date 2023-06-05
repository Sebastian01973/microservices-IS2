package uptc.edu.rolPermission.domain.repository;

import uptc.edu.permission.domain.models.Permission;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rolPermission.domain.model.RolPermission;

import java.util.List;
import java.util.Optional;

public interface RolPermissionRepository {
    List<RolPermission> findAll();
    Optional<RolPermission> findById(String id);
    RolPermission create(Rol rol, Permission permission);
    boolean delete(String id);

    List<RolPermission> findByRol(String idRol);
    Optional<RolPermission> update(int id, int idRol, int idPermission);
}
