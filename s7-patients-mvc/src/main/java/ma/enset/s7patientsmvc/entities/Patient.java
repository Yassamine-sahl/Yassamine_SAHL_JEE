package ma.enset.s7patientsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
//la notation DATA genere le constructeur sans parametres
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Accepte pas que le nom soit empty
    @NotEmpty
    //entre 4 et 40 caracteres
    @Size(min = 4, max = 40)
    private String nom;
    //Format de la date
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateN;
    private Boolean malade;
    //N'accepte pas un score qui est inferieur a 100
    @DecimalMin("100")
    private int score;
}


