package vn.ptit.services;

import vn.ptit.dtos.MovieDTO;
import vn.ptit.dtos.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO save(UserDTO userDTO);
    public List<UserDTO> findAll();
    public UserDTO findById(int id);
    public void delete(int id);
    public UserDTO login(String email, String password);
    public UserDTO findWithEmail(String email);
}
