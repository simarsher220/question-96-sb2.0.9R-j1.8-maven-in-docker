package org.codejudge.sb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codejudge.sb.entity.ShowMapping;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TheatreShowsDto {

    @JsonProperty("theatre_id")
    private Integer theatreId;

    @JsonProperty("theatre_name")
    private String theatreName;

    @JsonProperty("theatre_location")
    private String theatreLocation;

    @JsonProperty("city")
    private String city;

    @JsonProperty("pincode")
    private Integer pincode;
    private List<ShowMapping> shows;

    public TheatreShowsDto(TheatreShowsDtoBuilder builder) {
        this.theatreId = builder.theatreId;
        this.theatreName = builder.theatreName;
        this.theatreLocation = builder.theatreLocation;
        this.city = builder.city;
        this.pincode = builder.pincode;
        this.shows = builder.shows;
    }

    public static class TheatreShowsDtoBuilder {

        private Integer theatreId;
        private String theatreName;
        private String theatreLocation;
        private String city;
        private Integer pincode;
        private List<ShowMapping> shows;

        public TheatreShowsDtoBuilder(Integer theatreId, String theatreName, String theatreLocation, String city, Integer pincode, List<ShowMapping> shows) {
            this.theatreId = theatreId;
            this.theatreName = theatreName;
            this.theatreLocation = theatreLocation;
            this.city = city;
            this.pincode = pincode;
            this.shows = shows;
        }

        public TheatreShowsDto build() {
            return new TheatreShowsDto(this);
        }
    }

}

