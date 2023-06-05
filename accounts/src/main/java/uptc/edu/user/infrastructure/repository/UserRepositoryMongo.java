package uptc.edu.user.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uptc.edu.user.infrastructure.repository.dto.UserDto;

public interface UserRepositoryMongo extends MongoRepository<UserDto, String> {
    @Query("{'email':?0}")
    public UserDto getUserByMail(String email);

}
