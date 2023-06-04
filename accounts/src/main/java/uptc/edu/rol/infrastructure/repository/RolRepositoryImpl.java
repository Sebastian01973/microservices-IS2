package uptc.edu.rol.infrastructure.repository;

import org.springframework.stereotype.Component;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.domain.repository.RolRepository;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;
import uptc.edu.rol.infrastructure.repository.mapper.RolMapper;

import java.util.List;
import java.util.Optional;

@Component
public class RolRepositoryImpl implements RolRepository {

    private RolRepositoryMongo rolRepositoryMongo;

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
    public Optional<Rol> updateRol(Rol rol) {
        Optional<RolDto> currentRol = rolRepositoryMongo.findById(rol.id());
        if(currentRol != null) {
            currentRol.get().setName(rol.name());
            currentRol.get().setDescription(rol.description());
            return currentRol.map(rolRepositoryMongo::save).map(RolMapper::toDomain);
        }
        return Optional.empty();
    }

    @Override
    public Rol saveRol(Rol rol) {
         return RolMapper.toDomain(
                rolRepositoryMongo.save(RolMapper.toDto(rol))
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
