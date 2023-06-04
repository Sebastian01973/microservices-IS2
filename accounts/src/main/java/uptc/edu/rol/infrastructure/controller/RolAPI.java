package uptc.edu.rol.infrastructure.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.rol.application.*;
import uptc.edu.rol.infrastructure.controller.requests.RolCreateRequest;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RolAPI {

    private final CreateRolUseCase createRolUseCase;
    private final GetRolUseCase getRolUseCase;
    private final DeleteRolUseCase deleteRolUseCase;
    private final UpdateRolUseCase updateRolUseCase;
    private final GetAllRolUseCase getAllRolUseCase;

    public RolAPI(CreateRolUseCase createRolUseCase, GetRolUseCase getRolUseCase, DeleteRolUseCase deleteRolUseCase, UpdateRolUseCase updateRolUseCase, GetAllRolUseCase getAllRolUseCase) {
        this.createRolUseCase = createRolUseCase;
        this.getRolUseCase = getRolUseCase;
        this.deleteRolUseCase = deleteRolUseCase;
        this.updateRolUseCase = updateRolUseCase;
        this.getAllRolUseCase = getAllRolUseCase;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RolCreateRequest request) {
        var rol = createRolUseCase.invoke(request.name(), request.description());
        return new ResponseEntity<>(rol, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(getAllRolUseCase.invoke(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return getRolUseCase.invoke(id)
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody RolCreateRequest request) {
        return updateRolUseCase.invoke(id, request.name(), request.description())
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (deleteRolUseCase.invoke(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
