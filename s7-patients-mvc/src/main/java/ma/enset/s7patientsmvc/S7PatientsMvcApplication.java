package ma.enset.s7patientsmvc;

import ma.enset.s7patientsmvc.entities.Patient;
import ma.enset.s7patientsmvc.repositories.PatientRepository;
import ma.enset.s7patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class S7PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(S7PatientsMvcApplication.class, args);
    }

    @Bean
    //Permet de creer un password encoder
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Pour ne pas inserer a chaque fois ses donnes dans la BDD
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "Hajar", new Date(), false, 120));
            patientRepository.save(new Patient(null, "Yassmine", new Date(), true, 321));
            patientRepository.save(new Patient(null, "Aymane", new Date(), false, 165));
            patientRepository.save(new Patient(null, "Akram", new Date(), true, 132));

            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
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
