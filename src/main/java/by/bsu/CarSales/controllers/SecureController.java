package by.bsu.CarSales.controllers;

import by.bsu.CarSales.mappers.UserMapper;
import by.bsu.CarSales.models.DTO.UserDTO;
import by.bsu.CarSales.models.User;
import by.bsu.CarSales.services.SecureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@RestController
@ControllerAdvice
@RequestMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecureController {

    private final SecureService secureService;

    private UserMapper userMapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SecureController(SecureService secureService, UserMapper userMapper) {
        this.secureService = secureService;
        this.userMapper = userMapper;
    }


    @PostMapping("/user")
    public ResponseEntity<HttpResponse> registrationUser(@RequestBody @Valid UserDTO registrationUser, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                for (ObjectError o : bindingResult.getAllErrors()) {
                    log.warn("validation failed" + bindingResult.getFieldError());
                }
                throw new IllegalArgumentException();
            }
            User user = userMapper.mapUserDTOtoUser(registrationUser);
            if (secureService.registrationUser(user)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
