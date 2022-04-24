package vn.ptit.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ptit.dtos.OrderDTO;
import vn.ptit.services.OrderService;

@RestController
@RequestMapping(path = "/api/order")
public class OrderApi {
    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDTO){
        OrderDTO res = orderService.insert(orderDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
