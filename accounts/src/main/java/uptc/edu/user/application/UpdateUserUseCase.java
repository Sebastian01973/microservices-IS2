package uptc.edu.user.application;

import uptc.edu.common.UseCase;
import uptc.edu.user.domain.model.User;
import uptc.edu.user.domain.repository.UserRepository;

import java.util.Optional;

@UseCase
public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> invoke(String id, String name, String email, String password) {
        return userRepository.update(id, new User(id, name, email, password,null));
    }
}
