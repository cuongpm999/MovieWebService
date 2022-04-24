package vn.ptit.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ptit.dtos.MovieDTO;
import vn.ptit.dtos.MovieLinkDTO;
import vn.ptit.entities.Movie;
import vn.ptit.entities.MovieLink;
import vn.ptit.repositories.CountViewRepository;
import vn.ptit.repositories.MovieLinkRepository;
import vn.ptit.repositories.MovieRepository;
import vn.ptit.services.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieLinkRepository movieLinkRepository;
    @Autowired
    private CountViewRepository countViewRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public MovieDTO insert(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        movie.setMovieLinks(new ArrayList<>());

        List<MovieLinkDTO> movieLinkDTOS = movieDTO.getMovieLinks();
        for (MovieLinkDTO m : movieLinkDTOS) {
            MovieLink movieLink = modelMapper.map(m, MovieLink.class);
            movie.addMovieLink(movieLink);
        }

        movie = movieRepository.save(movie);

        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    @Transactional
    public MovieDTO update(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        movie.setMovieLinks(new ArrayList<>());

        if (!movieDTO.getMovieLinks().isEmpty()) {
            List<MovieLinkDTO> movieLinkDTOS = movieDTO.getMovieLinks();
            for (MovieLinkDTO m : movieLinkDTOS) {
                MovieLink movieLink = modelMapper.map(m, MovieLink.class);
                movie.addMovieLink(movieLink);
            }

            List<MovieLink> movieLinks = movieLinkRepository.findByMovie_Id(movie.getId());
            movieLinks.forEach(ml -> movieLinkRepository.deleteById(ml.getId()));
        }

        movie = movieRepository.save(movie);

        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movies = movieRepository.findByStatusTrue();
        List<MovieDTO> movieDTOS = new ArrayList<>();
        movies.forEach(m -> {
            MovieDTO movieDTO = modelMapper.map(m, MovieDTO.class);
            movieDTO.setCountView(countViewRepository.countCountViewByMovie_Id(movieDTO.getId()));
            movieDTOS.add(movieDTO);
        });
        return movieDTOS;
    }

    @Override
    public MovieDTO findById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            MovieDTO movieDTO = modelMapper.map(movie.get(), MovieDTO.class);
            movieDTO.setCountView(countViewRepository.countCountViewByMovie_Id(movieDTO.getId()));
            return movieDTO;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            Movie m = movie.get();
            m.setStatus(false);
            movieRepository.save(m);
        }
    }
}
