package com.banana.persistence;

import com.banana.models.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;

@Setter
@Getter
public class StudentsRepositoryJPA implements StudentsRepositoryInf {

    private EntityManager em;

    @Override
    public void add(Student estudiante) {
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
    }

    @Override
    public Student update(Student estudiante) {
        return null;
    }

    @Override
    public Student get(int idx) {
        return null;
    }

    @Override
    public Student getById(Long id) {
        return em.find(Student.class, id);
    }


}
