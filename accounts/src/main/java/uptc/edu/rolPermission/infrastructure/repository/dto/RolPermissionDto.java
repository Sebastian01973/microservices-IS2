package uptc.edu.rolPermission.infrastructure.repository.dto;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import uptc.edu.permission.infrastructure.repository.dto.PermissionDto;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;

@Data
@Document(collection = "rolPermission")
public class RolPermissionDto {

    @Id
    private String id;
    @DBRef
    private RolDto rol;
    @DBRef
    private PermissionDto permission;

    public RolPermissionDto(String id, RolDto rol, PermissionDto permission) {
        this.rol = rol;
        this.permission = permission;
    }
}
