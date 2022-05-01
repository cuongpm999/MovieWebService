package vn.ptit.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ptit.dtos.MovieDTO;
import vn.ptit.dtos.UserDTO;
import vn.ptit.services.MovieService;
import vn.ptit.services.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "*")
public class UserApi {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/insert", produces = "application/json")
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
        UserDTO res = userService.save(userDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/find-all", produces = "application/json")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> res = userService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") int id) {
        UserDTO res = userService.findById(id);
        if (res != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        UserDTO res = userService.save(userDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/login", produces = "application/json")
    public ResponseEntity<UserDTO> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        UserDTO res = userService.login(email, password);
        if (res != null)
            return new ResponseEntity<>(res, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/find-by-email/{email}", produces = "application/json")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable("email") String email) {
        UserDTO res = userService.findWithEmail(email);
        if (res != null)
            return new ResponseEntity<>(res, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/find-with-pagination", produces = "application/json")
    public ResponseEntity<List<UserDTO>> findWithPagination(@RequestParam(required = false, value = "page") Integer page,
                                                            @RequestParam("limit") int limit) {
        List<UserDTO> res = userService.findWithPagination(page, limit);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/count-all", produces = "application/json")
    public ResponseEntity<Long> totalUser() {
        return new ResponseEntity<>(userService.totalUser(), HttpStatus.OK);
    }
}
