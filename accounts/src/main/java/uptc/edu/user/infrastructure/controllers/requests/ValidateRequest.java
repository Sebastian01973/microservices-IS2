package uptc.edu.user.infrastructure.controllers.requests;

public record ValidateRequest(
        String email,
        String password
) {
}
