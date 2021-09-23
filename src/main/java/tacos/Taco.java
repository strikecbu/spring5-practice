package tacos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Least have 5 words")
    private String name;

    @Size(min = 1, message = "Must choose one!")
    private List<Ingredient> ingredients;
}
