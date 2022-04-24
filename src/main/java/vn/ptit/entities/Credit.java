package vn.ptit.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="credits")
@PrimaryKeyJoinColumn(name="paymentId")
@Data
public class Credit extends Payment{
    @Column(name = "number", nullable = false, length = 255)
    private String number;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "date", nullable = false, length = 255)
    private String date;
}
