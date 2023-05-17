package by.bsu.CarSales.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorites_seq")
    @SequenceGenerator(name = "favorites_seq", sequenceName = "favorites_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "car_id")
    private int carId;
}
