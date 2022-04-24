package ma.enset.s7patientsmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    //Afficher la page 403 quand l'utlisateur tente a acceder a une page dont il n'a pas le droit
    @GetMapping("/403")
    public String notAuthorized(){
        return "403";
    }
}
