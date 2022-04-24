package vn.ptit.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movie_links")
@Data
public class MovieLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url",nullable = false,length = 1000)
    private String url;

    @Column(name = "episodeName",nullable = false,length = 1000)
    private String episodeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movieId", nullable = false)
    private Movie movie;

}
