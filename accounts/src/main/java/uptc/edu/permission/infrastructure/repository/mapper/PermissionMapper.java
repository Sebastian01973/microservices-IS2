package uptc.edu.permission.infrastructure.repository.mapper;

import uptc.edu.permission.domain.models.Permission;
import uptc.edu.permission.infrastructure.repository.dto.PermissionDto;

import java.util.ArrayList;
import java.util.List;

public class PermissionMapper {

    public static PermissionDto toDto(Permission permission){
        return new PermissionDto(permission.id(), permission.url(), permission.method());
    }

    public static Permission toDomain(PermissionDto permissionDto){
        return new Permission(permissionDto.getId(), permissionDto.getUrl(), permissionDto.getMethod());
    }

    public static List<Permission> toDomain(List<PermissionDto> permissionDtoList){
        List<Permission> permissionList = new ArrayList<>();
        for (PermissionDto permissionDto : permissionDtoList) {
            permissionList.add(toDomain(permissionDto));
        }
        return permissionList;
    }
}
