package vn.ptit.services;

import vn.ptit.dtos.DealDTO;

import java.util.List;

public interface DealService {
    public DealDTO save(DealDTO dealDTO);
    public List<DealDTO> findAll();
    public void delete(int id);
    public DealDTO findById(int id);
}
