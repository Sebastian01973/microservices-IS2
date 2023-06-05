package uptc.edu.permission.infrastructure.repository;

import org.springframework.stereotype.Component;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.domain.repository.PermissionRepository;
import uptc.edu.permission.infrastructure.repository.dto.PermissionDto;
import uptc.edu.permission.infrastructure.repository.mapper.PermissionMapper;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;
import uptc.edu.rol.infrastructure.repository.mapper.RolMapper;

import java.util.List;
import java.util.Optional;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    private PermissionRepositoryMongo permissionRepositoryMongo;

    public PermissionRepositoryImpl(PermissionRepositoryMongo permissionRepositoryMongo) {
        this.permissionRepositoryMongo = permissionRepositoryMongo;
    }

    @Override
    public Optional<Permission> getPermissionById(String id) {
        Optional<PermissionDto> permissionDto = permissionRepositoryMongo.findById(id);
        return permissionDto.map(PermissionMapper::toDomain);
    }

    @Override
    public Optional<Permission> updatePermission(String id,Permission permission) {
        Optional<Permission> currentPermission = getPermissionById(id);
        if (!currentPermission.isEmpty()) {
            PermissionDto permissionDto = PermissionMapper.toDto(currentPermission.get());
            permissionDto.setUrl(permission.url());
            permissionDto.setMethod(permission.method());
            permissionRepositoryMongo.save(permissionDto);
        }
        currentPermission = getPermissionById(id);
        return currentPermission;
    }

    @Override
    public Permission savePermission(Permission permission) {
        return PermissionMapper.toDomain(
                permissionRepositoryMongo.save(PermissionMapper.toDto(permission))
        );
    }

    @Override
    public boolean deletePermission(String id) {
        return getPermissionById(id)
                .map(permission -> {
                    permissionRepositoryMongo.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<Permission> getAllPermissions() {
        List<PermissionDto> permissionDtoList = permissionRepositoryMongo.findAll();
        return PermissionMapper.toDomain(permissionDtoList);
    }

    @Override
    public Permission getPermissionByUrlAndMethod(String url, String method) {
        PermissionDto permissionDto = permissionRepositoryMongo.getPermission(url, method);
        return PermissionMapper.toDomain(permissionDto);
    }

}
