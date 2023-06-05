package uptc.edu.user.application;

import uptc.edu.common.UseCase;
import uptc.edu.user.domain.model.User;
import uptc.edu.user.domain.repository.UserRepository;

import java.util.List;

@UseCase
public class GetAllUsersUseCase {

    private final UserRepository userRepository;

    public GetAllUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> invoke() {
        return userRepository.findAllUser();
    }

}
