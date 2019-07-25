package com.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity(name = "movie")
@NamedQueries(value = {
        @NamedQuery(name = "Movie.getAll", query = "SELECT m FROM movie m")
})
public class Movie {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imdbid")
    @NotNull
    private Integer imdbid;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;


    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "release_year")
    private Date releaseYear;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "movies")
    @JoinColumn(name = "actors")
    @JsonIgnore
    private List<Actor> actors;

}
