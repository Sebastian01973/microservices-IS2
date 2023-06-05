package uptc.edu.permission.infrastructure.repository.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "permission")
public class PermissionDto {

    @Id
    private String id;
    private String url;
    private String method;

    public PermissionDto(String id, String url, String method) {
        this.id = id;
        this.url = url;
        this.method = method;
    }
}
