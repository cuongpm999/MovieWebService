package vn.ptit.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.ptit.entities.MovieLink;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private int id;
    private String name;
    private int year;
    private double imdb;
    private String country;
    private String director;
    private int time;
    private String quality;
    private String category;
    private String trailer;
    private String content;
    private String type;
    private int episodeNumber;
    private boolean status;
    private Date createdAt;
    private String image;
    private int countView;
    private List<MovieLinkDTO> movieLinks;
}
