package uptc.edu.rol.application;

import uptc.edu.common.UseCase;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.domain.repository.RolRepository;

import java.util.Optional;

@UseCase
public class UpdateRolUseCase {

    private final RolRepository rolRepository;

    public UpdateRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Optional<Rol> invoke(String id, String name, String description) {
        return rolRepository.updateRol(id,new Rol(id,name, description));
    }

}
