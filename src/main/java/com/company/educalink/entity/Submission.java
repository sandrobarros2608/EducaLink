package com.company.educalink.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Contenido entregado por el estudiante (puede ser texto o URL)
    private String content;

    // Fecha en la que se realizó la entrega
    private LocalDate submissionDate;

    // Indica si el estudiante entregó a tiempo
    private boolean submitted;

    // Observaciones adicionales de la entrega
    private String comments;

    // Relación con la tarea a la que corresponde la entrega
    @ManyToOne(optional = false)
    @JoinColumn(name = "homework_id")
    private Homework homework;

    // Relación con el estudiante que realizó la entrega
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    // Relación uno a uno con la nota correspondiente a esta entrega
    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private Qualification qualification;
}
