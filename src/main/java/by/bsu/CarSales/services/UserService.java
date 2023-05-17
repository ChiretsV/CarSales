package by.bsu.CarSales.services;

import by.bsu.CarSales.models.User;
import by.bsu.CarSales.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(int id) {
        try {
            User user = userRepository.findById(id).get();
            if (user == null) {
                throw new NoSuchElementException();
            } else {
                return user;
            }
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        return new User();
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public boolean updateUser(User user) {
        User userBySecurityContext = getUserBySecurityContext();
        if(userBySecurityContext.getRole().equals("USER")) {
            userBySecurityContext = user;
            if(user.getLogin().equals(userBySecurityContext.getLogin())) {
                user.setId(userBySecurityContext.getId());
                user.setPassword(passwordEncoder.encode(userBySecurityContext.getPassword()));
                user.setRole(userBySecurityContext.getRole());
                userRepository.saveAndFlush(user);
                return true;
            }
        } else if (userBySecurityContext.getRole().equals("ADMIN")) {
            List<User> users = getAllUser();
            for (User userCheck : users) {
                if (user.getLogin().equals(userCheck.getLogin())) {
                    user.setId(userCheck.getId());
                    user.setPassword(passwordEncoder.encode(userCheck.getPassword()));
                    user.setRole(userCheck.getRole());
                    userRepository.saveAndFlush(user);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        User userBySecurityContext = getUserBySecurityContext();
        if(userBySecurityContext.getRole().equals("USER")) {
            if(id == userBySecurityContext.getId()) {
                userRepository.deleteById(userBySecurityContext.getId());
                return true;
            }
        } else if (userBySecurityContext.getRole().equals("ADMIN")) {
            List<User> users = getAllUser();
            for (User userCheck : users) {
                if(id == userCheck.getId()) {
                    userRepository.deleteById(id);
                    return true;
                }
            }
        }
        return false;
    }

    public User getUserBySecurityContext() {
        return userRepository.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }


}
