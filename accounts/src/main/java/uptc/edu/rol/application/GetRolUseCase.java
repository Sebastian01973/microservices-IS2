package uptc.edu.rol.application;

import uptc.edu.common.UseCase;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.domain.repository.RolRepository;

import java.util.Optional;

@UseCase
public class GetRolUseCase {

    private final RolRepository rolRepository;


    public GetRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Optional<Rol> invoke(String id) {
        return rolRepository.getRolById(id);
    }

}
