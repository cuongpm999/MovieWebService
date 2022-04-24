package vn.ptit.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.dtos.DealDTO;
import vn.ptit.entities.Deal;
import vn.ptit.repositories.DealRepository;
import vn.ptit.services.DealService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DealServiceImpl implements DealService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DealRepository dealRepository;

    @Override
    public DealDTO save(DealDTO dealDTO) {
        Deal deal = modelMapper.map(dealDTO, Deal.class);
        deal = dealRepository.save(deal);
        return modelMapper.map(deal, DealDTO.class);
    }

    @Override
    public List<DealDTO> findAll() {
        List<Deal> deals = dealRepository.findByStatusTrue();
        List<DealDTO> dealDTOS = new ArrayList<>();
        deals.forEach(d -> dealDTOS.add(modelMapper.map(d, DealDTO.class)));
        return dealDTOS;
    }

    @Override
    public void delete(int id) {
        Deal deal = dealRepository.findById(id).get();
        deal.setStatus(false);
        dealRepository.save(deal);
    }

    @Override
    public DealDTO findById(int id) {
        Optional<Deal> deal = dealRepository.findById(id);
        if(deal.isPresent()){
            return modelMapper.map(deal.get(),DealDTO.class);
        }
        return null;
    }
}
