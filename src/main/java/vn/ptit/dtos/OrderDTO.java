package vn.ptit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private Date startAt;
    private Date endAt;
    private boolean status;
    private UserDTO user;
    private DealDTO deal;
    private PaymentDTO payment;
}
