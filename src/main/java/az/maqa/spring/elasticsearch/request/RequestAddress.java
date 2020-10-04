package az.maqa.spring.elasticsearch.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddress {
    private String country;
    private String city;
    private String street;
    private String zipCode;
}
