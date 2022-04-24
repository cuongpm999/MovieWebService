package vn.ptit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealDTO {
    private int id;
    private String name;
    private int month;
    private double price;
    private double discount;
    private boolean status;
}
