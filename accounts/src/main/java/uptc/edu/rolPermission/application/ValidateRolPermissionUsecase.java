package uptc.edu.rolPermission.application;

import uptc.edu.rolPermission.domain.model.RolPermission;
import uptc.edu.rolPermission.domain.repository.RolPermissionRepository;

public class ValidateRolPermissionUsecase {

    private final RolPermissionRepository rolPermissionRepository;

    public ValidateRolPermissionUsecase(RolPermissionRepository rolPermissionRepository) {
        this.rolPermissionRepository = rolPermissionRepository;
    }

    public RolPermission invoke(String idRol, String idPermission){
        return rolPermissionRepository.getRolPermissionByRolAndPermission(idRol, idPermission);
    }
}
