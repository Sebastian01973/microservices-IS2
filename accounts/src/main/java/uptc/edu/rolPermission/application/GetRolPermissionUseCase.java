package uptc.edu.rolPermission.application;

import uptc.edu.common.UseCase;
import uptc.edu.rolPermission.domain.model.RolPermission;
import uptc.edu.rolPermission.domain.repository.RolPermissionRepository;

import java.util.Optional;

@UseCase
public class GetRolPermissionUseCase {

    private final RolPermissionRepository repository;

    public GetRolPermissionUseCase(RolPermissionRepository repository) {
        this.repository = repository;
    }

    public Optional<RolPermission> invoke(String id) {
        return repository.findById(id);
    }
}
