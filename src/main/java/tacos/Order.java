package tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "PLACEDAT")
    private Date placedAt;

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

    @NotBlank(message = "Check ur credit number")
//    @CreditCardNumber(message = "Check ur credit number")
    @Column(name = "CCNUMBER")
    private String ccNumber;

    @Pattern(regexp = "(0[1-9]|1[1-2])(\\/)([2-9][0-9])", message = "Check ur expire date MM/YY")
    @Column(name = "CCEXPIRATION")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "check CVV")
    @Column(name = "CCCVV")
    private String ccCVV;


    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    private void placedAt(){
        this.placedAt = new Date();
    }

}
