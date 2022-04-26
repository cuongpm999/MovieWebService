package vn.ptit.services;

import vn.ptit.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    public OrderDTO insert(OrderDTO orderDTO);
    public List<OrderDTO> findOrderByUser(String email);
    public List<OrderDTO> findAll();
    public OrderDTO findById(int id);
    public boolean checkOrderExpire(String email);

}
