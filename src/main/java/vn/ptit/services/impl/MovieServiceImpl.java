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
import vn.ptit.utils.Pagination;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Comparator;
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
    @PersistenceContext
    private EntityManager entityManager;


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

    @Override
    public List<MovieDTO> search(String key, Integer page, int limit) {
        int pageNumber = 1;
        String jpql = "select m from Movie m where m.status = true and m.name like '%" + key + "%' or m.category like '%" +
                key + "%' or m.country like '%" + key + "%' or m.director like '%" +
                key + "%' or m.type like '%" + key + "%'";
        if (page != null) {
            pageNumber = page;
        }
        Query query = entityManager.createQuery(jpql, Movie.class);
        query.setFirstResult((pageNumber - 1) * limit);
        query.setMaxResults(limit);

        List<Movie> movies = query.getResultList();
        List<MovieDTO> movieDTOS = new ArrayList<>();
        movies.forEach(m -> {
            MovieDTO movieDTO = modelMapper.map(m, MovieDTO.class);
            movieDTO.setCountView(countViewRepository.countCountViewByMovie_Id(movieDTO.getId()));
            movieDTOS.add(movieDTO);
        });
        return movieDTOS;
    }

    @Override
    public List<MovieDTO> filter(String sort, Integer year, String type, String country, String category, Integer page, int limit) {
        int pageNumber = 1;
        String jpql = "select m from Movie m where m.status = true";
        if (year != null) {
            jpql += " and m.year = " + year;
        }
        if (type != null) {
            jpql += " and m.type = '" + type + "'";
        }
        if (country != null) {
            jpql += " and m.country = '" + country + "'";
        }
        if (category != null) {
            jpql += " and m.category like '%" + category + "%'";
        }
        if (sort != null) {
            if (sort.equalsIgnoreCase("post_time")) {
                jpql += " order by m.year,m.createdAt desc";
            } else if (sort.equalsIgnoreCase("update_time")) {
                jpql += " order by m.createdAt desc";
            }
        }
        Query query = entityManager.createQuery(jpql, Movie.class);
        List<Movie> movies = query.getResultList();
        List<MovieDTO> movieDTOS = new ArrayList<>();
        for(Movie m : movies){
            MovieDTO movieDTO = modelMapper.map(m, MovieDTO.class);
            movieDTO.setCountView(countViewRepository.countCountViewByMovie_Id(movieDTO.getId()));
            movieDTOS.add(movieDTO);
        };

        if (sort != null && sort.equalsIgnoreCase("count_view")) {
            movieDTOS.sort(new Comparator<MovieDTO>() {
                @Override
                public int compare(MovieDTO o1, MovieDTO o2) {
                    return o2.getCountView() - o1.getCountView();
                }
            });

        }
        if(page!=null){
            pageNumber = page;
        }
        movieDTOS = Pagination.paging(movieDTOS,pageNumber,limit);

        return movieDTOS;
    }
}
