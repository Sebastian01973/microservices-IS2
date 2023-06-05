package uptc.edu.user.infrastructure.repository.dto;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import uptc.edu.rol.infrastructure.repository.dto.RolDto;

@Data
@Document(collection = "user")
public class UserDto {

    @Id
    private String id;

    private String pseudonym;
    private String email;
    private String password;

    @DBRef
    private RolDto rol;

    public UserDto(String id, String pseudonym, String email, String password, RolDto rol) {
        this.id = id;
        this.pseudonym = pseudonym;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

}
