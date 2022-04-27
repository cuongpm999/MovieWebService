package vn.ptit.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ptit.dtos.CountViewDTO;
import vn.ptit.dtos.MovieDTO;
import vn.ptit.services.CountViewService;
import vn.ptit.services.MovieService;

@RestController
@RequestMapping(path = "/api/count-view")
@CrossOrigin(origins = "*")
public class CountViewApi {
    @Autowired
    private CountViewService countViewService;

    @PostMapping(path = "/insert", produces = "application/json")
    public ResponseEntity<CountViewDTO> insert(@RequestBody CountViewDTO countViewDTO) {
        CountViewDTO res = countViewService.insert(countViewDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
