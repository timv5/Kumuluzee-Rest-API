package com.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "movie")
@NamedQueries(value = {
        @NamedQuery(name = "Movie.getAll", query = "SELECT m FROM movie m"),
        @NamedQuery(name = "Movie.getMovieById", query = "SELECT m FROM movie m WHERE m.imdbid = :id"),
        @NamedQuery(name = "Movie.deleteMovieById", query = "DELETE FROM movie m WHERE m.imdbid = :id"),
        @NamedQuery(
                name = "Movie.updateMovie",
                query = "UPDATE movie m SET m.title = :title," +
                        "m.description = :description," +
                        "m.releaseYear = :releaseYear WHERE m.imdbid = :id"
        )
})
public class Movie {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imdbid")
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
