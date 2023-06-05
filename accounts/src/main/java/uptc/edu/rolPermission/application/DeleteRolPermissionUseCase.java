package uptc.edu.rolPermission.application;

import uptc.edu.common.UseCase;
import uptc.edu.rolPermission.domain.repository.RolPermissionRepository;

@UseCase
public class DeleteRolPermissionUseCase {

    private final RolPermissionRepository repository;

    public DeleteRolPermissionUseCase(RolPermissionRepository repository) {
        this.repository = repository;
    }

    public boolean invoke(String id) {
        return repository.delete(id);
    }
}
