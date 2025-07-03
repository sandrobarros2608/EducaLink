package com.company.educalink.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;              // Título de la tarea
    private String description;        // Descripción detallada de la tarea
    private LocalDate dueDate;         // Fecha límite para entregar la tarea
    private LocalDate creationDate;    // Fecha en la que fue creada la tarea

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Course course; // Curso al que pertenece la tarea

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Teacher teacher; // Profesor que creó la tarea

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("homework")
    private List<Submission> submissions;
}

