package vn.ptit.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.ptit.dtos.MovieDTO;
import vn.ptit.dtos.UserDTO;
import vn.ptit.entities.Movie;
import vn.ptit.entities.User;
import vn.ptit.repositories.UserRepository;
import vn.ptit.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findByStatusTrue();
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(u -> {
            userDTOS.add(modelMapper.map(u, UserDTO.class));
        });
        return userDTOS;
    }

    @Override
    public UserDTO findById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);
            return userDTO;
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setStatus(false);
            userRepository.save(u);
        }
    }

    @Override
    public UserDTO login(String email, String password) {
        User user = userRepository.findWithEmailAndPassword(email, password);
        if (user != null) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        }
        return null;
    }

    @Override
    public UserDTO findWithEmail(String email) {
        User user = userRepository.findWithEmail(email);
        if (user != null) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return userDTO;
        }
        return null;
    }

    @Override
    public List<UserDTO> findWithPagination(Integer page, int limit) {
        int pageNumber = 1;
        if (page != null) {
            pageNumber = page;
        }
        Pageable pageable =
                PageRequest.of(pageNumber - 1, limit);
        Page<User> users = userRepository.findByStatusTrue(pageable);
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(u -> {
            userDTOS.add(modelMapper.map(u, UserDTO.class));
        });
        return userDTOS;
    }

    @Override
    public long totalUser() {
        return userRepository.countWithStatusTrue();
    }

}
