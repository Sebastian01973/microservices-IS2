package uptc.edu.permission.application;

import uptc.edu.common.UseCase;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.domain.repository.PermissionRepository;

import java.util.Optional;

@UseCase
public class GetPermissionUseCase {

    private final PermissionRepository permissionRepository;

    public GetPermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Optional<Permission> invoke(String id) {
        return permissionRepository.getPermissionById(id);
    }

}
