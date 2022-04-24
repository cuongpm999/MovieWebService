package vn.ptit.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false,length = 255)
    private String name;

    @Column(name = "year",nullable = false)
    private int year;

    @Column(name = "imdb", nullable = false)
    private double imdb;

    @Column(name = "country",nullable = false,length = 255)
    private String country;

    @Column(name = "director",nullable = false,length = 255)
    private String director;

    @Column(name = "time",nullable = false)
    private int time;

    @Column(name = "quality",nullable = false,length = 255)
    private String quality;

    @Column(name = "category",nullable = false,length = 1000)
    private String category;

    @Column(name = "trailer",nullable = false,length = 255)
    private String trailer;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "type",nullable = false,length = 255)
    private String type;

    @Column(name = "episodeNumber",nullable = false)
    private int episodeNumber;

    @Column(name = "image",nullable = false,length = 1000)
    private String image;

    @Column(name = "status",nullable = true)
    private boolean status;

    @Column(name = "createdAt", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieLink> movieLinks = new ArrayList<>();

    public void addMovieLink(MovieLink movieLink) {
        movieLinks.add(movieLink);
        movieLink.setMovie(this);
    }

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
        this.status = true;
    }

}
