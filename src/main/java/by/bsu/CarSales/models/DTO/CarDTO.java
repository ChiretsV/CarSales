package by.bsu.CarSales.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    @NotBlank
    private String model;

    @NotBlank
    private String brand;

    @NotBlank
    private int price;

    @NotBlank
    private String description;

    @NotBlank
    private String city;

    @NotBlank
    private int mileage;

    @NotBlank
    private int yearOfManufacture;

    private UserDTO userDTO;
}
