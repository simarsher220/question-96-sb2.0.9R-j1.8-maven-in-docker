package org.codejudge.sb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.entity.Movie;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TheatresShowsDto {

    private Movie movie;
    private List<TheatreShowsDto> theatres;

    public TheatresShowsDto(TheatresShowsDtoBuilder builder) {
        this.movie = builder.movie;
        this.theatres = builder.theatres;
    }

    public static class TheatresShowsDtoBuilder {

        private Movie movie;
        private List<TheatreShowsDto> theatres;

        public TheatresShowsDtoBuilder(Movie movie, List<TheatreShowsDto> theatres) {
            this.movie = movie;
            this.theatres = theatres;
        }

        public TheatresShowsDto build() {
            return new TheatresShowsDto(this);
        }
    }
}
