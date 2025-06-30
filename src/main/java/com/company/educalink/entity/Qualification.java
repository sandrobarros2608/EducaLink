package com.company.educalink.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Valor numérico de la nota (por ejemplo, 4.5)
    private Double score;

    // Comentarios del profesor sobre la evaluación
    private String feedback;

    // Fecha en la que se asignó la nota
    private LocalDate evaluationDate;

    // Relación uno a uno con la entrega evaluada
    @OneToOne(optional = false)
    @JoinColumn(name = "submission_id", unique = true)
    private Submission submission;
}
