package uptc.edu.rolPermission.application;

import uptc.edu.common.UseCase;
import uptc.edu.rolPermission.domain.model.RolPermission;
import uptc.edu.rolPermission.domain.repository.RolPermissionRepository;

import java.util.List;

@UseCase
public class GetAllRolPermissionUseCase {

    private final RolPermissionRepository rolPermissionRepository;

    public GetAllRolPermissionUseCase(RolPermissionRepository rolPermissionRepository) {
        this.rolPermissionRepository = rolPermissionRepository;
    }

    public List<RolPermission> invoke() {
        return rolPermissionRepository.findAll();
    }
}
