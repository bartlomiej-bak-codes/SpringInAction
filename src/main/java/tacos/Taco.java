package tacos;


import lombok.Data;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Taco {
    
    @NotNull
    @Size(min=5, message = "The name must be at least 5 characters long.")
    private String name;
    @Size(min = 1, message = "You must select at least 1 ingredient.")
    private List<String> ingredients;
}
