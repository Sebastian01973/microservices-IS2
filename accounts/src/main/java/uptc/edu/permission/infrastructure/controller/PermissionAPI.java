package uptc.edu.permission.infrastructure.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.permission.application.*;
import uptc.edu.permission.infrastructure.controller.requests.PermissionCreateRequest;

import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/permissions")
public class PermissionAPI {

    private final CreatePermissionUseCase createPermissionUseCase;
    private final GetPermissionUseCase getPermissionUseCase;
    private final DeletePermissionUseCase deletePermissionUseCase;
    private final UpdatePermissionUseCase updatePermissionUseCase;
    private final GetAllPermissionUseCase getAllPermissionUseCase;
    private final PermissionUrlAndMethod permissionUrlAndMethod;

    public PermissionAPI(CreatePermissionUseCase createPermissionUseCase,
                         GetPermissionUseCase getPermissionUseCase,
                         DeletePermissionUseCase deletePermissionUseCase,
                         UpdatePermissionUseCase updatePermissionUseCase,
                         GetAllPermissionUseCase getAllPermissionUseCase,
                         PermissionUrlAndMethod permissionUrlAndMethod) {
        this.createPermissionUseCase = createPermissionUseCase;
        this.getPermissionUseCase = getPermissionUseCase;
        this.deletePermissionUseCase = deletePermissionUseCase;
        this.updatePermissionUseCase = updatePermissionUseCase;
        this.getAllPermissionUseCase = getAllPermissionUseCase;
        this.permissionUrlAndMethod = permissionUrlAndMethod;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PermissionCreateRequest request) {
        var permission = createPermissionUseCase.invoke(request.url(), request.method());
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(getAllPermissionUseCase.invoke(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return getPermissionUseCase.invoke(id)
                .map(permission -> new ResponseEntity<>(permission, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody PermissionCreateRequest request) {
        return updatePermissionUseCase.invoke(id, request.url(), request.method())
                .map(permission -> new ResponseEntity<>(permission, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (deletePermissionUseCase.invoke(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> getPermissionByUrlAndMethod(@RequestBody PermissionCreateRequest request) {
//        Logger.getLogger("PermissionAPI").info("url: " + request.url() + " method: " + request.method());
        if (request.url() != null && request.method() != null) {
            var permission = permissionUrlAndMethod.invoke(request.url(), request.method());
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
