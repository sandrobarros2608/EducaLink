package com.company.educalink.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.File;
import java.time.LocalDateTime;

@Entity
@Data
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

//    private File file;

    private LocalDateTime dueDate;




}
