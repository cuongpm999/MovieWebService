package vn.ptit.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "startAt", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date startAt;

    @Column(name = "endAt", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date endAt;

    @Column(name = "status", nullable = true)
    private boolean status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentId", nullable = false)
    private Payment payment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dealId", nullable = false)
    private Deal deal;

    @PrePersist
    void createdAt() {
        this.status = true;
        this.startAt = new Date();

        long monthTomSecond = 2592000000L;
        long endTime = this.startAt.getTime()+monthTomSecond;
        this.endAt = new Date(endTime);
    }

}
