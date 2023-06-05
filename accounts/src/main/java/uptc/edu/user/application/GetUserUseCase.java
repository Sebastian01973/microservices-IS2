package uptc.edu.user.application;

import uptc.edu.common.UseCase;
import uptc.edu.user.domain.model.User;
import uptc.edu.user.domain.repository.UserRepository;

import java.util.Optional;

@UseCase
public class GetUserUseCase {

    private final UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> invoke(String id) {
        return userRepository.getById(id);
    }

}
