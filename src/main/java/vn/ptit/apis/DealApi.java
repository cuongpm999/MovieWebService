package vn.ptit.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ptit.dtos.DealDTO;
import vn.ptit.services.DealService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/deal")
@CrossOrigin(origins = "*")
public class DealApi {
    @Autowired
    private DealService dealService;

    @PostMapping(path = "/insert", produces = "application/json")
    public ResponseEntity<DealDTO> insert(@RequestBody DealDTO dealDTO) {
        DealDTO res = dealService.save(dealDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/find-all", produces = "application/json")
    public ResponseEntity<List<DealDTO>> findAll() {
        List<DealDTO> res = dealService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}", produces = "application/json")
    public ResponseEntity<DealDTO> findAll(@PathVariable("id") int id) {
        DealDTO res = dealService.findById(id);
        if(res != null)
            return new ResponseEntity<>(res, HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<DealDTO> update(@RequestBody DealDTO dealDTO) {
        DealDTO res = dealService.save(dealDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        dealService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

}
