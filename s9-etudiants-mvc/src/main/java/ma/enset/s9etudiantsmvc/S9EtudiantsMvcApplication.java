package ma.enset.s9etudiantsmvc;

import ma.enset.s9etudiantsmvc.entities.Etudiant;
import ma.enset.s9etudiantsmvc.entities.Genre;
import ma.enset.s9etudiantsmvc.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class S9EtudiantsMvcApplication {

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
}
