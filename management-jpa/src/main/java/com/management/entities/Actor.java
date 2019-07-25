package com.management.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "actor")
@NamedQueries(value = {
        @NamedQuery(name = "Actor.getAll", query = "SELECT a FROM actor a")
})
public class Actor {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Integer id;


    @Getter
    @Setter
    @Column(name = "firstname")
    private String firstname;


    @Getter
    @Setter
    @Column(name = "lastname")
    private String lastname;

}
