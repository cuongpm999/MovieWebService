package vn.ptit.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ptit.dtos.CreditDTO;
import vn.ptit.dtos.DealDTO;
import vn.ptit.dtos.DigitalWalletDTO;
import vn.ptit.dtos.OrderDTO;
import vn.ptit.entities.Credit;
import vn.ptit.entities.DigitalWallet;
import vn.ptit.entities.Order;
import vn.ptit.repositories.CreditRepository;
import vn.ptit.repositories.DealRepository;
import vn.ptit.repositories.DigitalWalletRepository;
import vn.ptit.repositories.OrderRepository;
import vn.ptit.services.OrderService;
import vn.ptit.services.SendMailService;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private DigitalWalletRepository digitalWalletRepository;
    @Autowired private SendMailService sendMailService;


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

        try {
            order.setDeal(dealRepository.findById(order.getDeal().getId()).get());
            sendMailService.sendMail(order);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return orderDTO;
    }

    @Override
    public List<OrderDTO> findOrderByUser(String email) {
        List<Order> orders = orderRepository.findByUser_EmailOrderByStartAtDesc(email);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(o ->{
            OrderDTO orderDTO = modelMapper.map(o,OrderDTO.class);
            Optional<Credit> credit = creditRepository.findById(o.getPayment().getId());
            Optional<DigitalWallet> digitalWallet = digitalWalletRepository.findById(o.getPayment().getId());
            if(credit.isPresent())
                orderDTO.setPayment(modelMapper.map(credit.get(),CreditDTO.class));
            if(digitalWallet.isPresent())
                orderDTO.setPayment(modelMapper.map(digitalWallet.get(), DigitalWalletDTO.class));

            orderDTOS.add(orderDTO);
        });

        return orderDTOS;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(o ->{
            OrderDTO orderDTO = modelMapper.map(o,OrderDTO.class);
            Optional<Credit> credit = creditRepository.findById(o.getPayment().getId());
            Optional<DigitalWallet> digitalWallet = digitalWalletRepository.findById(o.getPayment().getId());
            if(credit.isPresent())
                orderDTO.setPayment(modelMapper.map(credit.get(),CreditDTO.class));
            if(digitalWallet.isPresent())
                orderDTO.setPayment(modelMapper.map(digitalWallet.get(), DigitalWalletDTO.class));

            orderDTOS.add(orderDTO);
        });

        return orderDTOS;
    }

    @Override
    public OrderDTO findById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            OrderDTO orderDTO = modelMapper.map(order.get(),OrderDTO.class);
            Optional<Credit> credit = creditRepository.findById(order.get().getPayment().getId());
            Optional<DigitalWallet> digitalWallet = digitalWalletRepository.findById(order.get().getPayment().getId());
            if(credit.isPresent())
                orderDTO.setPayment(modelMapper.map(credit.get(),CreditDTO.class));
            if(digitalWallet.isPresent())
                orderDTO.setPayment(modelMapper.map(digitalWallet.get(), DigitalWalletDTO.class));
            return orderDTO;
        }
        return null;
    }

    @Override
    public boolean checkOrderExpire(String email) {
        List<Order> orders = orderRepository.findByUser_EmailOrderByStartAtDesc(email);
        for(Order o : orders){
            long currentItem = new Date().getTime();
            long timeStart = o.getStartAt().getTime();
            long timeEnd = o.getEndAt().getTime();
            if(timeStart < currentItem && currentItem < timeEnd) return true;
        }
        return false;
    }

    @Override
    public List<OrderDTO> findWithPagination(Integer page, int limit) {
        int pageNumber = 1;
        if (page != null) {
            pageNumber = page;
        }
        Pageable pageable =
                PageRequest.of(pageNumber - 1, limit);
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(o ->{
            OrderDTO orderDTO = modelMapper.map(o,OrderDTO.class);
            Optional<Credit> credit = creditRepository.findById(o.getPayment().getId());
            Optional<DigitalWallet> digitalWallet = digitalWalletRepository.findById(o.getPayment().getId());
            if(credit.isPresent())
                orderDTO.setPayment(modelMapper.map(credit.get(),CreditDTO.class));
            if(digitalWallet.isPresent())
                orderDTO.setPayment(modelMapper.map(digitalWallet.get(), DigitalWalletDTO.class));

            orderDTOS.add(orderDTO);
        });

        return orderDTOS;
    }

    @Override
    public long totalOrder() {
        return orderRepository.count();
    }
}
