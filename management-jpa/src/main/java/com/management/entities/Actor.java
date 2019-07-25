package com.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

    @Getter
    @Setter
    @Column(name = "birth_date")
    private Date birthDate;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "movies_involved",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_imdb_id")
    )
    @JoinColumn(name = "movies")
    @JsonIgnore
    private List<Movie> movies;

}
