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
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDTO) {
        OrderDTO res = orderService.insert(orderDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/find-by-username/{email}")
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
    public ResponseEntity<Integer> findById(@PathVariable("email") String email) {
        boolean flag = orderService.checkOrderExpire(email);
        if (flag) return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

}
