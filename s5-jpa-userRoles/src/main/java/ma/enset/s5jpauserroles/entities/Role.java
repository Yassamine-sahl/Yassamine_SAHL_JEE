package ma.enset.s5jpauserroles.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Changer le nom de l'attribut
    @Column(name = "DESCRIPTION")
    private String desc;

    @Column(length = 20, unique = true)
    private String roleName;
    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "USERS_ROLES")

    //Pour ne pas retourner la liste des utilisateurs
    @ToString.Exclude

    //Pour une relation Bidirectionnelle, On ajoute cette annotation
    //Pour gerer la boucle infinie
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users = new ArrayList<>();
}
