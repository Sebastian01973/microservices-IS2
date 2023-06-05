package uptc.edu.user.infrastructure.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.rol.application.GetRolUseCase;
import uptc.edu.user.application.*;
import uptc.edu.user.infrastructure.controllers.requests.UserCreateRequest;
import uptc.edu.user.infrastructure.controllers.requests.ValidateRequest;
import uptc.edu.utils.ConvertSHA256;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserAPI {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ValidateUserUseCase validateUserUseCase;

    private final GetRolUseCase getRolUseCase;

    public UserAPI(GetAllUsersUseCase getAllUsersUseCase,
                   CreateUserUseCase createUserUseCase,
                   GetUserUseCase getUserUseCase,
                   DeleteUserUseCase deleteUserUseCase,
                   UpdateUserUseCase updateUserUseCase,
                   ValidateUserUseCase validateUserUseCase,
                   GetRolUseCase getRolUseCase) {

        this.getAllUsersUseCase = getAllUsersUseCase;
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.validateUserUseCase = validateUserUseCase;
        this.getRolUseCase = getRolUseCase;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(getAllUsersUseCase.invoke(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id_rol}")
    public ResponseEntity<?> create(@PathVariable String id_rol, @RequestBody UserCreateRequest userCreateRequest) {
        var rol = getRolUseCase.invoke(id_rol).orElse(null);
        if (rol == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            var user = createUserUseCase.invoke(userCreateRequest.pseudonym(),
                    userCreateRequest.email(),
                    ConvertSHA256.convertSHA256(userCreateRequest.password()),
                    rol);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return getUserUseCase.invoke(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UserCreateRequest request) {

        return updateUserUseCase.invoke(id, request.pseudonym(), request.email(), ConvertSHA256.convertSHA256(request.password()))
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (deleteUserUseCase.invoke(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/validate")
    public ResponseEntity<?> login(@RequestBody ValidateRequest validateRequest, final HttpServletResponse response) throws IOException {
        var validate = validateUserUseCase.invoke(validateRequest.email(), validateRequest.password());
        if (validate != null) {
            return new ResponseEntity<>(validate, HttpStatus.OK);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
}
