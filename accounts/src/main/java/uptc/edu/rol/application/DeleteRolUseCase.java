package uptc.edu.rol.application;

import uptc.edu.common.UseCase;
import uptc.edu.rol.domain.repository.RolRepository;

@UseCase
public class DeleteRolUseCase {

    private final RolRepository rolRepository;

    public DeleteRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public boolean invoke(String id) {
        return rolRepository.deleteRol(id);
    }

}
