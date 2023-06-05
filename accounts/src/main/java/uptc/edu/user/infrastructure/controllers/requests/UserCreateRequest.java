package uptc.edu.user.infrastructure.controllers.requests;

public record UserCreateRequest(
        String pseudonym,
        String email,
        String password
) {
}
