package uptc.edu.user.domain.model;

import uptc.edu.rol.domain.models.Rol;

public record User(
        String id,
        String pseudonym,
        String email,
        String password,

        Rol rol
) {
}
