package vn.ptit.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.dtos.CountViewDTO;
import vn.ptit.entities.CountView;
import vn.ptit.entities.Movie;
import vn.ptit.repositories.CountViewRepository;
import vn.ptit.services.CountViewService;

@Service
public class CountViewServiceImpl implements CountViewService {
    @Autowired
    private CountViewRepository countViewRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CountViewDTO insert(CountViewDTO countViewDTO) {
        CountView countView = modelMapper.map(countViewDTO,CountView.class);
        Movie movie = new Movie();
        movie.setId(countViewDTO.getMovieId());
        countView.setMovie(movie);
        countView = countViewRepository.save(countView);
        return modelMapper.map(countView,CountViewDTO.class);
    }
}
