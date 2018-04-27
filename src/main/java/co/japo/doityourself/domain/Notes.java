package co.japo.doityourself.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String notes;
    @OneToOne
    private Recipe recipe;

    public Notes(){

    }

    public Notes(String notes){
        this.notes = notes;
    }


}
