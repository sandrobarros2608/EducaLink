package com.company.educalink.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Contenido entregado por el estudiante (puede ser texto o URL)
    private String content;

    // Fecha en la que se realizó la entrega
    private LocalDate submissionDate;

    // Indica si el estudiante entregó a tiempo
    private boolean submitted = false;

    // Observaciones adicionales de la entrega
    private String comments;

    // Relación con el estudiante que realizó la entrega
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "homework_id")
    @JsonIgnoreProperties({"teacher", "course"})
    private Homework homework;

    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Qualification qualification;
}
