package uptc.edu.permission.application;

import uptc.edu.common.UseCase;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.domain.repository.PermissionRepository;

import java.util.List;

@UseCase
public class GetAllPermissionUseCase {

    private final PermissionRepository permissionRepository;

    public GetAllPermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> invoke() {
        return permissionRepository.getAllPermissions();
    }

}
