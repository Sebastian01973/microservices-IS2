package uptc.edu.rol.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;

public interface RolRepositoryMongo extends MongoRepository<RolDto, String> {

}
