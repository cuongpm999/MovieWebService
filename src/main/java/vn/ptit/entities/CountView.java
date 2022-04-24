package vn.ptit.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "count_views")
@Data
public class CountView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ipAddress",nullable = false,length = 255)
    private String ipAddress;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    @PrePersist
    void created() {
        this.date = new Date();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movieId", nullable = false)
    private Movie movie;
}
