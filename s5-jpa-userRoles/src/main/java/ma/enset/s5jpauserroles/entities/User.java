package ma.enset.s5jpauserroles.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(name = "USER_NAME", unique = true, length = 20)
    private String userName;

    //Pour ne pas afficher le mot de passe
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
