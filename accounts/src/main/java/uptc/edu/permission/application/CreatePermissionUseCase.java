package uptc.edu.permission.application;

import uptc.edu.common.UseCase;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.domain.repository.PermissionRepository;

@UseCase
public class CreatePermissionUseCase {

    private final PermissionRepository permissionRepository;

    public CreatePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission invoke(String url, String method) {
        return permissionRepository.savePermission(new Permission(null, url, method));
    }

}
