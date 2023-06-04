package uptc.edu.rolPermission.application;

import uptc.edu.common.BaseUseCase;
import uptc.edu.common.UseCase;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rolPermission.domain.model.RolPermission;
import uptc.edu.rolPermission.domain.repository.RolPermissionRepository;

@UseCase
public class CreateRolPermissionUsecase{

    private final RolPermissionRepository repository;

    public CreateRolPermissionUsecase(RolPermissionRepository repository) {
        this.repository = repository;
    }


    public RolPermission invoke(Rol rol, Permission permission) {
        return repository.create(rol, permission);
    }

}
