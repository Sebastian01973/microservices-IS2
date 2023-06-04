package uptc.edu.permission.infrastructure.repository;

import org.springframework.stereotype.Component;
import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.domain.repository.PermissionRepository;
import uptc.edu.permission.infrastructure.repository.dto.PermissionDto;
import uptc.edu.permission.infrastructure.repository.mapper.PermissionMapper;

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
    public Optional<Permission> updatePermission(Permission permission) {
        Optional<PermissionDto> currentPermission = permissionRepositoryMongo.findById(permission.id());
        if (currentPermission.isPresent()) {
            currentPermission.get().setUrl(permission.url());
            currentPermission.get().setMethod(permission.method());
            return currentPermission.map(permissionRepositoryMongo::save).map(PermissionMapper::toDomain);
        }
        return Optional.empty();
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
}
