package by.bsu.CarSales.services;

import by.bsu.CarSales.models.Car;
import by.bsu.CarSales.repositories.CarRepository;
import by.bsu.CarSales.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public Car getCarById(int id) {
        try {
            Car car = carRepository.findById(id).get();
            if (car != null) {
                return car;
            }
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
        }
        return new Car();
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public boolean createCar(Car car) {
        List<Car> cars = getAllCars();
        for (Car checkCar : cars) {
            if (car.getId() == checkCar.getId()) {
                return false;
            }
        }
        carRepository.save(car);
        return true;
    }

    public boolean updateCar(Car car) {
        List<Car> cars = getAllCars();
        for (Car checkCar : cars) {
            if (car.getId() == checkCar.getId()) {
                carRepository.saveAndFlush(car);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCarById(int id) {
        List<Car> cars = getAllCars();
        for (Car checkCar : cars) {
            if (id == checkCar.getId()) {
                carRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

}
