package com.company.educalink.entity;

import com.company.educalink.entity.interfaces.Nameable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Teacher implements Nameable {

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

    /* ManyToMany with Course. */
    @ManyToMany(mappedBy = "teachers")
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    /* OneToMany with Announcement. */
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    // Break cycle.
    @JsonIgnore
    private List<Announcement> announcements = new ArrayList<>();

    /* OneToMany with Publication. */
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    // Break cycle.
    @JsonIgnore
    private List<Publication> publications = new ArrayList<>();
}
