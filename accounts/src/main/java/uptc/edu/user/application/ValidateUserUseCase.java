package uptc.edu.user.application;

import uptc.edu.common.UseCase;
import uptc.edu.user.domain.model.User;
import uptc.edu.user.domain.repository.UserRepository;

import java.util.Optional;

@UseCase
public class ValidateUserUseCase {

    private final UserRepository userRepository;

    public ValidateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User invoke(String email, String password) {
        return userRepository.validateUser(email, password);
    }
}
