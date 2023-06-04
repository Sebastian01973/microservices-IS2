package uptc.edu.rol.application;

import org.springframework.stereotype.Component;
import uptc.edu.common.UseCase;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.rol.domain.repository.RolRepository;


@UseCase
public class CreateRolUseCase {

    private RolRepository rolRepository;

    public CreateRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol invoke(String name, String description) {
        return rolRepository.saveRol(new Rol(null, name, description));
    }
}
