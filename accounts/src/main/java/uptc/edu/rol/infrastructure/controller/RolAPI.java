package uptc.edu.rol.infrastructure.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.rol.application.CreateRolUseCase;
import uptc.edu.rol.infrastructure.controller.requests.RolCreateRequest;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RolAPI {

    @Autowired
    private CreateRolUseCase createRolUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RolCreateRequest request){
        var rol = createRolUseCase.invoke(request.name(), request.description());
        return new ResponseEntity<>(rol, HttpStatus.CREATED);
    }
}
