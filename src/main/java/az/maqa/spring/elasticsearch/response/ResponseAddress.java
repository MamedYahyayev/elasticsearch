package az.maqa.spring.elasticsearch.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddress {
    private String country;
    private String city;
    private String street;
    private String zipCode;
}
