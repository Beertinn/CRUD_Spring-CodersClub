package dev.bertin.coders_club.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Para indicar que es autoincrementable
    private Integer id;

    private String nombre;
    private String apellido;
    private Integer membresia;

}
