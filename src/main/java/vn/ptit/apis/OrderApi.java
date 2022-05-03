package vn.ptit.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ptit.dtos.OrderDTO;
import vn.ptit.services.OrderService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
@CrossOrigin(origins = "*")
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDTO) {
        OrderDTO res = orderService.insert(orderDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<List<OrderDTO>> findByUser(@PathVariable("email") String email) {
        List<OrderDTO> res = orderService.findOrderByUser(email);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<OrderDTO> res = orderService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") int id) {
        OrderDTO res = orderService.findById(id);
        if (res != null) return new ResponseEntity<>(res, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/check-order-expire/{email}")
    public ResponseEntity<Integer> checkOrderExpire(@PathVariable("email") String email) {
        boolean flag = orderService.checkOrderExpire(email);
        if (flag) return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping(path = "/find-with-pagination", produces = "application/json")
    public ResponseEntity<List<OrderDTO>> findWithPagination(@RequestParam(required = false, value = "page") Integer page,
                                                             @RequestParam("limit") int limit) {
        List<OrderDTO> res = orderService.findWithPagination(page, limit);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/count-all", produces = "application/json")
    public ResponseEntity<Long> totalOrder() {
        return new ResponseEntity<>(orderService.totalOrder(), HttpStatus.OK);
    }

}
