package uptc.edu.permission.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uptc.edu.permission.infrastructure.repository.dto.PermissionDto;

public interface PermissionRepositoryMongo extends MongoRepository<PermissionDto, String> {

    @Query("{'url':?0 , 'method':?1}")
    PermissionDto getPermission(String url, String method);

}
