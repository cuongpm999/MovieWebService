package vn.ptit.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ptit.dtos.CreditDTO;
import vn.ptit.dtos.DigitalWalletDTO;
import vn.ptit.dtos.OrderDTO;
import vn.ptit.entities.Credit;
import vn.ptit.entities.DigitalWallet;
import vn.ptit.entities.Order;
import vn.ptit.repositories.CreditRepository;
import vn.ptit.repositories.DigitalWalletRepository;
import vn.ptit.repositories.OrderRepository;
import vn.ptit.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private DigitalWalletRepository digitalWalletRepository;


    @Override
    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO,Order.class);
        if(orderDTO.getPayment() instanceof DigitalWalletDTO){
            DigitalWalletDTO digitalWalletDTO = (DigitalWalletDTO) orderDTO.getPayment();
            DigitalWallet digitalWallet = modelMapper.map(digitalWalletDTO,DigitalWallet.class);
            digitalWallet = digitalWalletRepository.save(digitalWallet);
            order.setPayment(digitalWallet);
        }
        if(orderDTO.getPayment() instanceof CreditDTO){
            CreditDTO creditDTO = (CreditDTO) orderDTO.getPayment();
            Credit credit = modelMapper.map(creditDTO,Credit.class);
            credit = creditRepository.save(credit);
            order.setPayment(credit);
        }

        order = orderRepository.save(order);
        orderDTO = modelMapper.map(order,OrderDTO.class);
        return orderDTO;
    }
}
