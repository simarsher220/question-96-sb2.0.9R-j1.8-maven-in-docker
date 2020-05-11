package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    @JsonProperty("movie_id")
    private Integer movieId;

    @Column(name = "movie_name", unique = true)
    @JsonProperty("movie_name")
    private String movieName;

    @Column(name = "movie_trailer")
    @JsonProperty("movie_trailer")
    private String movieTrailer;

    @Column(name = "movie_overview", columnDefinition="LONGTEXT")
    @JsonProperty("movie_overview")
    private String movieOverview;

    @Column(name = "movie_poster")
    @JsonProperty("movie_poster")
    private String moviePoster;

    @Column(name = "length")
    private Integer length;

    public static void validateForUpsertion(Movie movie) {
        movie.validateLength();
        movie.validatePoster();
        movie.validateOverview();
        movie.validateName();
        movie.validateTrailer();
    }

    private void validateTrailer() {
        if (StringUtils.isEmpty(movieTrailer)) {
            throw new IllegalArgumentException("Movie trailer can't be empty");
        }
    }

    private void validateLength() {
        if (null == length) {
            throw new IllegalArgumentException("Movie length can't be empty");
        }
    }

    private void validatePoster() {
        if (StringUtils.isEmpty(moviePoster)) {
            throw new IllegalArgumentException("Movie poster can't be empty");
        }
    }

    private void validateOverview() {
        if (StringUtils.isEmpty(movieOverview)) {
            throw new IllegalArgumentException("Movie overview can't be empty");
        }
    }

    private void validateName() {
        if (StringUtils.isEmpty(movieName)) {
            throw new IllegalArgumentException("Movie name can't be empty");
        }
    }
}
