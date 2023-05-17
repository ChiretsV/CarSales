package by.bsu.CarSales.mappers;

import by.bsu.CarSales.models.Car;
import by.bsu.CarSales.models.DTO.CarDTO;
import by.bsu.CarSales.models.DTO.UserDTO;
import by.bsu.CarSales.models.User;
import by.bsu.CarSales.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    private UserService userService;

    private UserMapper userMapper;

    public CarMapper(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public Car mapCarDTOtoCar (CarDTO carDTO) {
        Car car = new Car();
        car.setPrice(carDTO.getPrice());
        car.setModel(carDTO.getModel());
        car.setBrand(carDTO.getBrand());
        car.setCity(carDTO.getCity());
        car.setDescription(carDTO.getDescription());
        car.setMileage(carDTO.getMileage());
        car.setYearOfManufacture(carDTO.getYearOfManufacture());
        car.setUser(userService.getUserBySecurityContext());
        return car;
    }

    public CarDTO mapCarToCarDTO (Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setPrice(car.getPrice());
        carDTO.setModel(car.getModel());
        carDTO.setBrand(car.getBrand());
        carDTO.setCity(car.getCity());
        carDTO.setDescription(car.getDescription());
        carDTO.setMileage(car.getMileage());
        carDTO.setYearOfManufacture(car.getYearOfManufacture());
        carDTO.setUserDTO(userMapper.mapUserToUserDTO(userService.getUserById(car.getUser().getId())));
        return carDTO;
    }
}
