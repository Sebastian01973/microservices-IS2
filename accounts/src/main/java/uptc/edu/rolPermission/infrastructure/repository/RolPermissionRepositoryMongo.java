package uptc.edu.rolPermission.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uptc.edu.rolPermission.infrastructure.repository.dto.RolPermissionDto;



public interface RolPermissionRepositoryMongo extends MongoRepository<RolPermissionDto, String> {

     @Query("{'rol.$id': ObjectId(?0), 'permission.$id':ObjectId(?1)}")
     public RolPermissionDto getRolPermissionByRolAndPermission(String idRol, String idPermission);


}
