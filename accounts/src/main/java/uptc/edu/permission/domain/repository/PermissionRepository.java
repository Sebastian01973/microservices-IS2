package uptc.edu.permission.domain.repository;

import uptc.edu.permission.domain.models.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {
    Optional<Permission> getPermissionById(String id);

    Permission savePermission(Permission permission);

    boolean deletePermission(String id);

    Optional<Permission> updatePermission(Permission permission);

    List<Permission> getAllPermissions();
}
