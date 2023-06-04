package uptc.edu.rolPermission.infrastructure.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.permission.application.GetPermissionUseCase;
import uptc.edu.rol.application.GetRolUseCase;
import uptc.edu.rolPermission.application.CreateRolPermissionUseCase;
import uptc.edu.rolPermission.application.GetAllRolPermissionUseCase;

@CrossOrigin
@RestController
@RequestMapping("/rol-permission")
public class RolPermissionAPI {

    private final CreateRolPermissionUseCase createRolPermissionUsecase;
    private final GetRolUseCase getRolUseCase;
    private final GetPermissionUseCase getPermissionUseCase;
    private final GetAllRolPermissionUseCase getAllRolUseCase;

    public RolPermissionAPI(CreateRolPermissionUseCase createRolPermissionUsecase, GetAllRolPermissionUseCase getAllRolUseCase, GetRolUseCase getRolUseCase, GetPermissionUseCase getPermissionUseCase) {
        this.createRolPermissionUsecase = createRolPermissionUsecase;
        this.getAllRolUseCase = getAllRolUseCase;
        this.getRolUseCase = getRolUseCase;
        this.getPermissionUseCase = getPermissionUseCase;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permission/{id_permission}")
    public ResponseEntity<?> create(@PathVariable String id_rol, @PathVariable String id_permission) {
        var rol = getRolUseCase.invoke(id_rol).orElse(null);
        var permission = getPermissionUseCase.invoke(id_permission).orElse(null);
        if (rol == null || permission == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            createRolPermissionUsecase.invoke(rol, permission);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(getAllRolUseCase.invoke(), HttpStatus.OK);
    }


}
