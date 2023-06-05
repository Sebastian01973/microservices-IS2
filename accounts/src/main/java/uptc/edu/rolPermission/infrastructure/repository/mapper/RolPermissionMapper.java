package uptc.edu.rolPermission.infrastructure.repository.mapper;

import uptc.edu.permission.infrastructure.repository.mapper.PermissionMapper;
import uptc.edu.rol.infrastructure.repository.mapper.RolMapper;
import uptc.edu.rolPermission.domain.model.RolPermission;
import uptc.edu.rolPermission.infrastructure.repository.dto.RolPermissionDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RolPermissionMapper {

    public static RolPermission toDomain(RolPermissionDto dto) {
        return new RolPermission(
                dto.getId(),
                RolMapper.toDomain(dto.getRol()),
                PermissionMapper.toDomain(dto.getPermission())
        );
    }

    public static RolPermissionDto toDto(RolPermission domain) {
        return new RolPermissionDto(
                domain.id(),
                RolMapper.toDto(domain.rol()),
                PermissionMapper.toDto(domain.permission())
        );
    }

    public static List<RolPermission> toDomain(List<RolPermissionDto> dtoList) {
        List<RolPermission> rolPermissionArrayList = new ArrayList<>();
        for (RolPermissionDto dto : dtoList) {
            rolPermissionArrayList.add(toDomain(dto));
        }
        return rolPermissionArrayList;
    }
}
