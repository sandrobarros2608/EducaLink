package com.company.educalink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The field Name is required")
    private String name;

    @NotBlank(message = "The field Last Name is required")
    private String lastName;

    @NotBlank(message = "The field Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "The field Password is required")
    @Size(min = 8, message = "The password must contain at least 8 characters.")
    private String password;

    @NotBlank(message = "The field Phone Number is required")
    @Size(min = 10, max = 10, message = "The Phone Number must contain 10 digits")
    private String phoneNumber;

    /* Grade */
    @NotNull(message = "The Grade is required")
    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;
}
