package com.crud.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.Accessors;



import java.io.Serializable;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Column(nullable = true, name = "email")
    private String email;

}
