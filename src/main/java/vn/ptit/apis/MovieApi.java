package vn.ptit.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ptit.dtos.MovieDTO;
import vn.ptit.services.MovieService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/movie")
public class MovieApi {
    @Autowired
    private MovieService movieService;

    @PostMapping(path = "/insert", produces = "application/json")
    public ResponseEntity<MovieDTO> insert(@RequestBody MovieDTO movieDTO) {
        MovieDTO res = movieService.insert(movieDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/find-all", produces = "application/json")
    public ResponseEntity<List<MovieDTO>> findAll() {
        List<MovieDTO> res = movieService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}", produces = "application/json")
    public ResponseEntity<MovieDTO> findById(@PathVariable("id") int id) {
        MovieDTO res = movieService.findById(id);
        if (res != null) return new ResponseEntity<>(res, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity<MovieDTO> update(@RequestBody MovieDTO movieDTO) {
        MovieDTO res = movieService.update(movieDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
        movieService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public ResponseEntity<List<MovieDTO>> search(@RequestParam(value = "key") String key,
                                                 @RequestParam(required = false, value = "page") Integer page,
                                                 @RequestParam("limit") int limit) {
        List<MovieDTO> res = movieService.search(key, page, limit);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/filter", produces = "application/json")
    public ResponseEntity<List<MovieDTO>> filter(@RequestParam(required = false, value = "sort") String sort,
                                                 @RequestParam(required = false, value = "year") Integer year,
                                                 @RequestParam(required = false, value = "type") String type,
                                                 @RequestParam(required = false, value = "country") String country,
                                                 @RequestParam(required = false, value = "category") String category,
                                                 @RequestParam(required = false, value = "page") Integer page,
                                                 @RequestParam("limit") int limit) {
        List<MovieDTO> res = movieService.filter(sort, year, type, country, category, page, limit);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/same-movie", produces = "application/json")
    public ResponseEntity<List<MovieDTO>> sameMovie(@RequestParam("id") int id,
                                                    @RequestParam("category") String category,
                                                    @RequestParam("limit") int limit) {
        List<MovieDTO> res = movieService.sameMovie(id, category, limit);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/count-all", produces = "application/json")
    public ResponseEntity<Integer> countAll() {
        return new ResponseEntity<>(movieService.countAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/count-search", produces = "application/json")
    public ResponseEntity<Integer> countSearch(@RequestParam(value = "key") String key) {
        return new ResponseEntity<>(movieService.countSearch(key), HttpStatus.OK);
    }

    @GetMapping(path = "/count-filter", produces = "application/json")
    public ResponseEntity<Integer> countFilter(@RequestParam(required = false, value = "year") Integer year,
                                               @RequestParam(required = false, value = "type") String type,
                                               @RequestParam(required = false, value = "country") String country,
                                               @RequestParam(required = false, value = "category") String category) {
        return new ResponseEntity<>(movieService.countFilter(year, type, country, category), HttpStatus.OK);
    }
}
