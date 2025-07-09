package com.company.educalink.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Valor numérico de la nota (por ejemplo, 4.5)
    private Double score;

    // Comentarios del profesor sobre la evaluación
    private String feedback;

    // Fecha en la que se asignó la nota
    private LocalDate evaluationDate = LocalDate.now();

    @OneToOne(optional = false)
    @JoinColumn(name = "submission_id", unique = true, nullable = false)
    private Submission submission;
}
