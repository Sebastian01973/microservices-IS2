package uptc.edu.permission.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uptc.edu.permission.infrastructure.repository.dto.PermissionDto;

public interface PermissionRepositoryMongo extends MongoRepository<PermissionDto, String> {
}
