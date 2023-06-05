package uptc.edu.user.infrastructure.repository.mapper;

import uptc.edu.rol.infrastructure.repository.mapper.RolMapper;
import uptc.edu.user.domain.model.User;
import uptc.edu.user.infrastructure.repository.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User toDomain(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getPseudonym(),
                userDto.getEmail(),
                userDto.getPassword(),
                RolMapper.toDomain(userDto.getRol())
        );
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.id(),
                user.pseudonym(),
                user.email(),
                user.password(),
                RolMapper.toDto(user.rol())
        );
    }

     public static List<User> toDomain(List<UserDto> userDtoList){
        List<User> userList = new ArrayList<>();
        for (UserDto dto : userDtoList) {
            userList.add(toDomain(dto));
        }
        return userList;
    }
}
