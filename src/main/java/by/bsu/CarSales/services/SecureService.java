package by.bsu.CarSales.services;

import by.bsu.CarSales.models.User;
import by.bsu.CarSales.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class SecureService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SecureService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public boolean registrationUser(User user) {
        try {
            if (userRepository.getUserByLogin(user.getLogin()) != null) {
                throw new LoginException("login already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
            User savedUser = userRepository.save(user);
            return true;
        } catch (LoginException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
