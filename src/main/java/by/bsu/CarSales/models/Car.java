package by.bsu.CarSales.models;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(name = "car_seq", sequenceName = "cars_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "yearOfManufacture")
    private int yearOfManufacture;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
