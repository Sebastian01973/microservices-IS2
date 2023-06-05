package uptc.edu.rolPermission.domain.model;

import uptc.edu.permission.domain.models.Permission;
import uptc.edu.rol.domain.models.Rol;

public record RolPermission(
        String id,
        Rol rol,
        Permission permission
) {
}
