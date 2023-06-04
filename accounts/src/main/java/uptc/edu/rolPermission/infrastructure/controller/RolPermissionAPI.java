package uptc.edu.rolPermission.infrastructure.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uptc.edu.permission.application.GetPermissionUseCase;
import uptc.edu.rol.application.GetRolUseCase;
import uptc.edu.rolPermission.application.CreateRolPermissionUsecase;

@CrossOrigin
@RestController
@RequestMapping("/rol-permission")
public class RolPermissionAPI {

    private final CreateRolPermissionUsecase createRolPermissionUsecase;

    private final GetRolUseCase getRolUseCase;
    private final GetPermissionUseCase getPermissionUseCase;

    public RolPermissionAPI(CreateRolPermissionUsecase createRolPermissionUsecase, GetRolUseCase getRolUseCase, GetPermissionUseCase getPermissionUseCase) {
        this.createRolPermissionUsecase = createRolPermissionUsecase;
        this.getRolUseCase = getRolUseCase;
        this.getPermissionUseCase = getPermissionUseCase;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permission/{id_permission}")
    public void create(@PathVariable String id_rol, @PathVariable String id_permission) {
        var rol = getRolUseCase.invoke(id_rol).orElse(null);
        var permission = getPermissionUseCase.invoke(id_permission).orElse(null);
        createRolPermissionUsecase.invoke(rol, permission);
    }


}
