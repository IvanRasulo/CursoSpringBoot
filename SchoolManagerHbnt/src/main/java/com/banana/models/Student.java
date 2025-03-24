package com.banana.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "estudiante")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nombre;

    private String apellido;

    private int curso;

    /*@Transient
    private String campo_no;*/

    public boolean isValid() {
        return this.nombre != null && this.apellido != null && this.curso > 0;
    }
}
