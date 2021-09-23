package tacos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATEDAT")
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Least have 5 words")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "Must choose one!")
    private List<Ingredient> ingredients;

    @PrePersist
    private void createdAt(){
        this.createdAt = new Date();
    }
}
