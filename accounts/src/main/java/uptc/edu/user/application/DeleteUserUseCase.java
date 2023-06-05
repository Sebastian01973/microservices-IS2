package uptc.edu.user.application;

import uptc.edu.common.UseCase;
import uptc.edu.user.domain.repository.UserRepository;

@UseCase
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean invoke(String id) {
        return userRepository.delete(id);
    }
}
