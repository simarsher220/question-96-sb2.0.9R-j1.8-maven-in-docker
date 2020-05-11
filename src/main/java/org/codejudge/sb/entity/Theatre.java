package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "theatre", uniqueConstraints=
@UniqueConstraint(columnNames={"theatre_name", "pincode"}))
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Theatre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("theatre_id")
    @Column(name = "theatre_id")
    private Integer theatreId;

    @JsonProperty("theatre_name")
    @Column(name = "theatre_name")
    private String theatreName;

    @JsonProperty("theatre_location")
    @Column(name = "theatre_location")
    private String theatreLocation;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private Integer pincode;

    public static void validateForUpsertion(Theatre theatre) {
        theatre.validateName();
        theatre.validateLocation();
        theatre.validatePincode();
        theatre.validateCity();
    }

    private void validateName() {
        if (StringUtils.isEmpty(theatreName)) {
            throw new IllegalArgumentException("Theatre name can't be empty!");
        }
    }

    private void validateLocation() {
        if (StringUtils.isEmpty(theatreLocation)) {
            throw new IllegalArgumentException("Theatre location can't be empty!");
        }
    }

    private void validatePincode() {
        if (null == pincode) {
            throw new IllegalArgumentException("pincode can't be empty!");
        }
    }

    private void validateCity() {
        if (StringUtils.isEmpty(city)) {
            throw new IllegalArgumentException("city name can't be empty!");
        }

    }
}
