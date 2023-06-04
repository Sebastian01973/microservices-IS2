package uptc.edu.permission.application;

import uptc.edu.common.UseCase;
import uptc.edu.permission.domain.repository.PermissionRepository;

@UseCase
public class DeletePermissionUseCase {

    private final PermissionRepository permissionRepository;

    public DeletePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public boolean invoke(String id) {
        return permissionRepository.deletePermission(id);
    }
}
