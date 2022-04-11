package ma.enset.s6patientsmvc;

import ma.enset.s6patientsmvc.entities.Patient;
import ma.enset.s6patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class S6PatientsMvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(S6PatientsMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "Hajar", new Date(), false, 12));
            patientRepository.save(new Patient(null, "Yassmine", new Date(), true, 321));
            patientRepository.save(new Patient(null, "Aymane", new Date(), false, 65));
            patientRepository.save(new Patient(null, "Akram", new Date(), true, 32));

            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });
        };
    }
}
