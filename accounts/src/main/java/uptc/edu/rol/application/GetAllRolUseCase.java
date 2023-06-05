package uptc.edu.rol.application;

import uptc.edu.common.UseCase;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.domain.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@UseCase
public class GetAllRolUseCase {

    private final RolRepository rolRepository;

    public GetAllRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> invoke() {
        return rolRepository.getAllRoles();
    }
}
