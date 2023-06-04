package uptc.edu.permission.infrastructure.controller.requests;

public record PermissionCreateRequest(
        String url,
        String method
) {
}
