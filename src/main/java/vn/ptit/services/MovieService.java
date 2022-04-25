package vn.ptit.services;

import vn.ptit.dtos.MovieDTO;

import java.util.List;

public interface MovieService {
    public MovieDTO insert(MovieDTO movieDTO);

    public MovieDTO update(MovieDTO movieDTO);

    public List<MovieDTO> findAll();

    public MovieDTO findById(int id);

    public void delete(int id);

    public List<MovieDTO> search(String key, Integer page, int limit);

    public List<MovieDTO> filter(String sort, Integer year, String type, String country, String category, Integer page, int limit);
    public List<MovieDTO> sameMovie(int id, String category, int limit);
}
