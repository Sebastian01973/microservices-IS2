package uptc.edu.user.infrastructure.repository;

import org.springframework.stereotype.Component;
import uptc.edu.user.domain.model.User;
import uptc.edu.user.domain.repository.UserRepository;
import uptc.edu.user.infrastructure.repository.dto.UserDto;
import uptc.edu.user.infrastructure.repository.mapper.UserMapper;
import uptc.edu.utils.ConvertSHA256;

import java.util.List;
import java.util.Optional;


@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryMongo userRepositoryMongo;

    public UserRepositoryImpl(UserRepositoryMongo userRepositoryMongo) {
        this.userRepositoryMongo = userRepositoryMongo;
    }

    @Override
    public List<User> findAllUser() {
        List<UserDto> userDtoList = userRepositoryMongo.findAll();
        return UserMapper.toDomain(userDtoList);
    }

    @Override
    public User create(User user) {
        return UserMapper.toDomain(
                userRepositoryMongo.save(
                        UserMapper.toDto(user)
                )
        );
    }

    @Override
    public Optional<User> update(String id, User user) {
        Optional<User> currentUser = getById(id);
        if (!currentUser.isEmpty()) {
            UserDto userDto = UserMapper.toDto(currentUser.get());
            userDto.setPseudonym(user.pseudonym());
            userDto.setEmail(user.email());
            userDto.setPassword(user.password());
            userRepositoryMongo.save(userDto);
        }
        currentUser = getById(id);
        return currentUser;
    }

    @Override
    public boolean delete(String id) {
        return getById(id)
                .map(user -> {
                    userRepositoryMongo.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<User> getById(String id) {
        Optional<UserDto> userDto = userRepositoryMongo.findById(id);
        return userDto.map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> toAssignRolToUser(String idUser, String idRol) {
        return Optional.empty();
    }



    @Override
    public User validateUser(String email, String password) {
        UserDto userDto = userRepositoryMongo.getUserByMail(email);
        if (userDto != null) {
            if (userDto.getPassword().equals(ConvertSHA256.convertSHA256(password))) {
                userDto.setPassword((""));
                return UserMapper.toDomain(userDto);
            }
        }
        return null;
    }
}
