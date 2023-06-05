package uptc.edu.rolPermission.application;

import uptc.edu.common.UseCase;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rolPermission.domain.model.RolPermission;
import uptc.edu.rolPermission.domain.repository.RolPermissionRepository;

@UseCase
public class CreateRolPermissionUseCase {

    private final RolPermissionRepository repository;

    public CreateRolPermissionUseCase(RolPermissionRepository repository) {
        this.repository = repository;
    }


    public RolPermission invoke(Rol rol, Permission permission) {
        return repository.create(rol, permission);
    }

}
