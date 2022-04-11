package ma.enset.s6patientsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String nom;
    //Format de la date
    @Temporal(TemporalType.DATE)
    private Date dateN;
    private Boolean malade;
    private int score;
}


