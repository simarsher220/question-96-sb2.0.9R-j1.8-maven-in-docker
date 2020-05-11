package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "show_mapping")
@Entity
public class ShowMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("show_id")
    private Integer id;

    @Column(name = "movie_id")
    @JsonProperty("movie_id")
    private Integer movieId;

    @Column(name = "theatre_id")
    @JsonProperty("theatre_id")
    private Integer theatreId;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    public ShowMapping(ShowMappingBuilder builder) {
        this.id = builder.id;
        this.movieId = builder.movieId;
        this.theatreId = builder.theatreId;
        this.date = builder.date;
        this.time = builder.time;
    }

    public static class ShowMappingBuilder {

        private Integer id;
        private Integer movieId;
        private Integer theatreId;
        private Date date;
        private Time time;

        public ShowMappingBuilder(Integer id, Integer movieId, Integer theatreId, Date date, Time time) {
            this.id = id;
            this.movieId = movieId;
            this.theatreId = theatreId;
            this.date = date;
            this.time = time;
        }

        public ShowMapping build() {
            return new ShowMapping(this);
        }
    }

    public static void validateForUpsertion(ShowMapping showMapping) {
        showMapping.validateMovieId();
        showMapping.validateTheatreId();
        showMapping.validateDate();
        showMapping.validateTime();
    }

    private void validateMovieId() {
        if (null == movieId) {
            throw new IllegalArgumentException("movie id can't be empty");
        }
    }

    private void validateTheatreId() {
        if (null == theatreId) {
            throw new IllegalArgumentException("theatre id can't be empty");
        }
    }

    private void validateDate() {
        if (null == date) {
            throw new IllegalArgumentException("date can't be empty");
        }
    }

    private void validateTime() {
        if (null == time) {
            throw new IllegalArgumentException("time can't be empty");
        }
    }

}
