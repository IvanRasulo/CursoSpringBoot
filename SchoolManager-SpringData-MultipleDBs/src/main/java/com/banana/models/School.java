package com.banana.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //    @Transient
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "mySchool")
//    @JoinColumn(name = "school_id")
//    @ToString.Exclude
    private List<Student> estudiantes = new ArrayList<>();

    public School(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addStudent(Student estudiante) {
        estudiantes.add(estudiante);
    }

    public void removeStudent(Student estudiante) {
        estudiantes.remove(estudiante);
    }

}
