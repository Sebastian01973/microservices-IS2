package uptc.edu.rolPermission.infrastructure.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.permission.application.GetPermissionUseCase;
import uptc.edu.rol.application.GetRolUseCase;
import uptc.edu.rolPermission.application.CreateRolPermissionUseCase;
import uptc.edu.rolPermission.application.DeleteRolPermissionUseCase;
import uptc.edu.rolPermission.application.GetAllRolPermissionUseCase;
import uptc.edu.rolPermission.application.GetRolPermissionUseCase;
import uptc.edu.user.application.UpdateUserUseCase;
import uptc.edu.user.application.ValidateUserUseCase;

@CrossOrigin
@RestController
@RequestMapping("/rol-permission")
public class RolPermissionAPI {

    private final CreateRolPermissionUseCase createRolPermissionUsecase;
    private final GetRolUseCase getRolUseCase;
    private final GetPermissionUseCase getPermissionUseCase;
    private final GetAllRolPermissionUseCase getAllRolUseCase;

    private final DeleteRolPermissionUseCase deleteRolPermissionUseCase;
    private final GetRolPermissionUseCase getRolPermissionUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ValidateUserUseCase validateUserUseCase;

    public RolPermissionAPI(CreateRolPermissionUseCase createRolPermissionUsecase,
                            GetAllRolPermissionUseCase getAllRolUseCase,
                            GetRolUseCase getRolUseCase,
                            GetPermissionUseCase getPermissionUseCase,
                            DeleteRolPermissionUseCase deleteRolPermissionUseCase,
                            GetRolPermissionUseCase getRolPermissionUseCase,
                            UpdateUserUseCase updateUserUseCase,
                            ValidateUserUseCase validateUserUseCase) {

        this.createRolPermissionUsecase = createRolPermissionUsecase;
        this.getAllRolUseCase = getAllRolUseCase;
        this.getRolUseCase = getRolUseCase;
        this.getPermissionUseCase = getPermissionUseCase;
        this.deleteRolPermissionUseCase = deleteRolPermissionUseCase;
        this.getRolPermissionUseCase = getRolPermissionUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.validateUserUseCase = validateUserUseCase;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permission/{id_permission}")
    public ResponseEntity<?> create(@PathVariable String id_rol, @PathVariable String id_permission) {
        var rol = getRolUseCase.invoke(id_rol).orElse(null);
        var permission = getPermissionUseCase.invoke(id_permission).orElse(null);
        if (rol == null || permission == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            var rolPermission = createRolPermissionUsecase.invoke(rol, permission);
            return new ResponseEntity<>(rolPermission,HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(getAllRolUseCase.invoke(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return getRolPermissionUseCase.invoke(id)
                .map(rolPermission -> new ResponseEntity<>(rolPermission, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (deleteRolPermissionUseCase.invoke(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/validate-permission/rol/{id_rol}")
    public ResponseEntity<?> validatePermission(@PathVariable String id_rol, @RequestParam String permission) {

        return new ResponseEntity<>(validateUserUseCase.invoke(id_rol, permission), HttpStatus.OK);
    }

}
