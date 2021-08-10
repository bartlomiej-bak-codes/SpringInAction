package tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Order {
    private Long id;
    private Date placedAt;
    @NotBlank(message = "Providing your name and surname is obligatory.")
    private String name;
    @NotBlank(message = "Entering the street is obligatory.")
    private String street;
    @NotBlank(message = "Entering the city is obligatory.")
    private String city;
    @NotBlank(message = "Providing the voivodeship is obligatory.")
    private String state;
    @NotBlank(message = "The postal code is obligatory.")
    private String zip;
    @CreditCardNumber(message = "This is not a valid credit card number.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "The value must be in the format MM / YY.")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV code.")
    private String ccCVV;

}
