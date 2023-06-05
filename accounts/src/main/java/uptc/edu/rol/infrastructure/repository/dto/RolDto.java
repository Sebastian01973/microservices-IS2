package uptc.edu.rol.infrastructure.repository.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "rol")
public class RolDto {

    @Id
    private String id;
    private String name;
    private String description;

    public RolDto(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}


