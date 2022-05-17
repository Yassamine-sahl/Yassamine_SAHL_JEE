package ma.enset.s9etudiantsmvc;

import ma.enset.s9etudiantsmvc.entities.Etudiant;
import ma.enset.s9etudiantsmvc.entities.Genre;
import ma.enset.s9etudiantsmvc.repository.EtudiantRepository;
import ma.enset.s9etudiantsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class S9EtudiantsMvcApplication {

    //Permet de creer un password encoder
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(S9EtudiantsMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository) {
        return args -> {
            etudiantRepository.save(new Etudiant(null, "SAHL","Yassamine","sahl@gmail.com",new Date(), Genre.FEMININ,false));
            etudiantRepository.save(new Etudiant(null, "SADEK","Youssef","sadek@gmail.com",new Date(), Genre.MASCULIN,false));
            etudiantRepository.save(new Etudiant(null, "FREJ","Houda","frej@gmail.com",new Date(), Genre.FEMININ,true));
            etudiantRepository.save(new Etudiant(null, "FATAR","Salma","fatar@gmail.com",new Date(), Genre.FEMININ,true));

            etudiantRepository.findAll().forEach(etudiant -> {
                System.out.println(etudiant.getNom());
            });
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("yassmine","1234","1234");
            securityService.saveNewUser("oumaima","1234","1234");
            securityService.saveNewUser("salma","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("yassmine","USER");
            securityService.addRoleToUser("yassmine","ADMIN");
            securityService.addRoleToUser("oumaima","USER");
            securityService.addRoleToUser("salma","USER");

        };
    }
}
