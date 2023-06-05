package uptc.edu.rol.infrastructure.repository;

import org.springframework.stereotype.Component;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.domain.repository.RolRepository;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;
import uptc.edu.rol.infrastructure.repository.mapper.RolMapper;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class RolRepositoryImpl implements RolRepository {

    private final RolRepositoryMongo rolRepositoryMongo;

    public RolRepositoryImpl(RolRepositoryMongo rolRepositoryMongo) {
        this.rolRepositoryMongo = rolRepositoryMongo;
    }

    @Override
    public Optional<Rol> getRolById(String id) {
        Optional<RolDto> rolDto = rolRepositoryMongo.findById(id);
//        rolDto.map(rol -> RolMapper.toDomain(rol)); remplazado por: lambda pro
        return rolDto.map(RolMapper::toDomain);
    }

    @Override
    public Optional<Rol> updateRol(String id, Rol rol) {
        Optional<Rol> currentRol = getRolById(id);
        if (!currentRol.isEmpty()) {
            RolDto rolDto = RolMapper.toDto(currentRol.get());
            rolDto.setName(rol.name());
            rolDto.setDescription(rol.description());
            rolRepositoryMongo.save(rolDto);
        }
        currentRol = getRolById(id);
        return currentRol;
    }

    @Override
    public Rol saveRol(Rol rol) {
        return RolMapper.toDomain(
                rolRepositoryMongo.save(
                        RolMapper.toDto(rol)
                )
        );
    }

    @Override
    public boolean deleteRol(String id) {
        return getRolById(id)
                .map(rol -> {
                    rolRepositoryMongo.deleteById(id);
                    return true;
                })
                .orElse(false);
    }


    @Override
    public List<Rol> getAllRoles() {
        List<RolDto> rolDtoList = rolRepositoryMongo.findAll();
        return RolMapper.toDomain(rolDtoList);
    }

}
