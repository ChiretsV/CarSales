package by.bsu.CarSales.controllers;

import by.bsu.CarSales.mappers.CarMapper;
import by.bsu.CarSales.models.Car;
import by.bsu.CarSales.models.DTO.CarDTO;
import by.bsu.CarSales.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {
    private final CarService carService;

    private final CarMapper carMapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable int id) {
        Car car = carService.getCarById(id);
        CarDTO carDTO = carMapper.mapCarToCarDTO(car);
        return new ResponseEntity<>(carDTO, car.getId() != 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<ArrayList<CarDTO>> getAllCars() {
        if (!carService.getAllCars().isEmpty()) {
            List<Car> cars = carService.getAllCars();
            ArrayList<CarDTO> carDTOS = new ArrayList<>();
            for (Car car : cars) {
                carDTOS.add(carMapper.mapCarToCarDTO(car));
            }
            return new ResponseEntity<>(carDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createCar(@RequestBody CarDTO carDTO) {
        Car car = carMapper.mapCarDTOtoCar(carDTO);
        carService.createCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateCar(@RequestBody CarDTO carDTO) {
        Car car = carMapper.mapCarDTOtoCar(carDTO);
        if (carService.updateCar(car)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCarById(@PathVariable int id) {
        try {
            if (carService.deleteCarById(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}