package uptc.edu.user.application;

import uptc.edu.common.UseCase;
import uptc.edu.rol.domain.models.Rol;
import uptc.edu.user.domain.model.User;
import uptc.edu.user.domain.repository.UserRepository;

@UseCase
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User invoke(String pseudonym, String email, String password, Rol rol) {
        return userRepository.create(new User(null, pseudonym, email, password,rol));
    }

}
