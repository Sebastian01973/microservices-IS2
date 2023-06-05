package uptc.edu.permission.application;

import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.domain.repository.PermissionRepository;
import uptc.edu.rolPermission.domain.model.RolPermission;

public class PermissionUrlAndMethod {

    private final PermissionRepository permissionRepository;


    public PermissionUrlAndMethod(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission invoke(String url, String method) {
        return permissionRepository.getPermissionByUrlAndMethod(url, method);
    }
}
