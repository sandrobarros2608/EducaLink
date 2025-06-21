package com.company.educalink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The Grade Name is required")
    private String name;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    // Break cycle
    @JsonIgnore
    private List<Student> students = new ArrayList<>();
}
