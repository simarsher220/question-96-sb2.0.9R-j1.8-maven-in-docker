package org.codejudge.sb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.entity.Theatre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TheatresDto {

    private List<Theatre> theatres;

    public TheatresDto(TheatresDtoBuilder builder) {
        this.theatres = builder.theatres;
    }

    public static class TheatresDtoBuilder {

        private List<Theatre> theatres;

        public TheatresDtoBuilder(List<Theatre> theatres) {
            this.theatres = theatres;
        }

        public TheatresDto build() {
            return new TheatresDto(this);
        }
    }

}
