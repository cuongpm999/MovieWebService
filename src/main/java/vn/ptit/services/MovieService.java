package vn.ptit.services;

import vn.ptit.dtos.MovieDTO;

import java.util.List;

public interface MovieService {
    public MovieDTO insert(MovieDTO movieDTO);
    public MovieDTO update(MovieDTO movieDTO);
    public List<MovieDTO> findAll();
    public MovieDTO findById(int id);
    public void delete(int id);
}
