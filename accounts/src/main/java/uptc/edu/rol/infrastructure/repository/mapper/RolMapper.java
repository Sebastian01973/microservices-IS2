package uptc.edu.rol.infrastructure.repository.mapper;

import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;

import java.util.ArrayList;
import java.util.List;

public class RolMapper {

    public static RolDto toDto(Rol rol) {
        return new RolDto(rol.id(), rol.name(), rol.description());
    }

    public static Rol toDomain(RolDto rolDto) {
        return new Rol(rolDto.getId(), rolDto.getName(), rolDto.getDescription());
    }

    public static List<Rol> toDomain(List<RolDto> rolDtoList) {
        List<Rol> rolList = new ArrayList<>();
        for (RolDto rolDto : rolDtoList) {
            rolList.add(toDomain(rolDto));
        }
        return rolList;
    }

    public static List<RolDto> toDto(List<Rol> rolList) {
        List<RolDto> rolDtoList = new ArrayList<>();
        for (Rol rol : rolList) {
            rolDtoList.add(toDto(rol));
        }
        return rolDtoList;
    }


}
