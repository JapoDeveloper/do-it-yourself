package co.japo.doityourself.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;
    @ManyToOne
    private Recipe recipe;

    public Ingredient(){

    }

    public Ingredient(BigDecimal amount, String description){
        this.amount = amount;
        this.description = description;
    }

    public Ingredient(BigDecimal amount, String description, UnitOfMeasure uom){
        this.amount = amount;
        this.description = description;
        this.uom = uom;
    }

}
