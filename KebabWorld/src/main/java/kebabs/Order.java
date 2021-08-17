package kebabs;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name ="Kebab_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @NotBlank(message = "Podanie imienia i nazwiska jest obowiązkowe")
    private String deliveryName;

    @NotBlank(message = "Podanie ulicy jest obowiązkowe")
    private String street;

    @NotBlank(message = "Podanie miasta jest obowiązkowe")
    private String city;

    @NotBlank(message = "Podanie województwa jest obowiązkowe")
    private String state;

    @NotBlank(message = "Podanie kodu pocztowego jest obowiązkowe")
    private String zip;

    @CreditCardNumber
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "The value must be in the format MM / YY.")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Kebab.class)
    private List<Kebab> kebabs = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addDesign(Kebab design){
        this.kebabs.add(design);
    }

    @PrePersist
    void placedAt(){
        this.placedAt= new Date();
    }
}
