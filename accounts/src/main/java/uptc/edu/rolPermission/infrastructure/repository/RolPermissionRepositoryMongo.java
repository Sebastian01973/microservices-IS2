package uptc.edu.rolPermission.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uptc.edu.rolPermission.infrastructure.repository.dto.RolPermissionDto;



public interface RolPermissionRepositoryMongo extends MongoRepository<RolPermissionDto, String> {

}
