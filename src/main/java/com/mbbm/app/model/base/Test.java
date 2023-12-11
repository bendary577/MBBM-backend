package com.mbbm.app.model.base;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    public void setName(String raghav) {
    }
}
