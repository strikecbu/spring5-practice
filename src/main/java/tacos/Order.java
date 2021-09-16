package tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Order {
    @NotBlank(message = "Must have name")
    private String name;

    @NotBlank(message = "Must have street")
    private String street;

    @NotBlank(message = "Must have city")
    private String city;
    @NotBlank(message = "Must have state")
    private String state;
    @NotBlank(message = "Must have zip")
    private String zip;

    @CreditCardNumber(message = "Check ur credit number")
    private String ccNumber;

    @Pattern(regexp = "(0[1-9]|1[1-2])(\\/)([2-9][0-9])", message = "Check ur expire date MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "check CVV")
    private String ccCVV;


}
