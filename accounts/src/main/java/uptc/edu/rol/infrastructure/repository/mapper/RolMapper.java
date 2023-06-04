package uptc.edu.rol.infrastructure.repository.mapper;

import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;

public class RolMapper {

    public static RolDto toDto(Rol rol) {
        return new RolDto(rol.id(), rol.name(), rol.description());
    }

    public static Rol toDomain(RolDto rolDto) {
        return new Rol(rolDto.getId(), rolDto.getName(), rolDto.getDescription());
    }
}
