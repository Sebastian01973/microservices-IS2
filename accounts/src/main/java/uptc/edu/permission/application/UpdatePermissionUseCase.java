package uptc.edu.permission.application;

import uptc.edu.common.UseCase;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.domain.repository.PermissionRepository;

import java.util.Optional;

@UseCase
public class UpdatePermissionUseCase {

    private final PermissionRepository permissionRepository;

    public UpdatePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Optional<Permission> invoke(String id, String name, String description) {
        return permissionRepository.updatePermission(id,new Permission(id, name, description));
    }
}
