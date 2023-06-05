package uptc.edu.user.domain.repository;

import uptc.edu.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAllUser();
    User create(User user);
    Optional<User> update(String id,User user);
    boolean delete(String id);
    Optional<User> getById(String id);

    Optional<User> toAssignRolToUser(String idUser, String idRol);

    User validateUser(String email, String password);
}
