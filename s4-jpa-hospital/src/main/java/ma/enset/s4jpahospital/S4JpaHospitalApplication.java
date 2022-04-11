package ma.enset.s4jpahospital;

import ma.enset.s4jpahospital.entities.*;
import ma.enset.s4jpahospital.repositories.ConsultationRepository;
import ma.enset.s4jpahospital.repositories.MedecinRepository;
import ma.enset.s4jpahospital.repositories.PatientRepository;
import ma.enset.s4jpahospital.repositories.RendezVousRepository;
import ma.enset.s4jpahospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class S4JpaHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(S4JpaHospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(IHospitalService hospitalService,
                            PatientRepository patientRepository,
                            RendezVousRepository rendezVousRepository,
                            MedecinRepository medecinRepository) {
        return args -> {
            //List de trois patients
            Stream.of("Hamouda", "Hajar", "Oumaima").forEach(name -> {
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDateN(new Date());
                patient.setMalade(false);
                hospitalService.savePatient(patient);
            });

            //List de trois medecin
            Stream.of("Aymane", "Yassmine", "Akram").forEach(name -> {
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setSpecialite(Math.random() > 0.5 ? "Cardio" : "Dentiste");
                medecin.setEmail(name + "@gmail.com");
                hospitalService.saveMedecin(medecin);
            });

            //Chercher un patient par son id
            Patient patient = patientRepository.findById(1L).orElse(null);

            //Chercher un patient par son nom
            Patient patient1 = patientRepository.findByNom("Hamouda");

            //Chercher un medecin par son nom
            Medecin medecin = medecinRepository.findByNom("Yassmine");

            //Creation d'un rdv d'un patient et medecin qui existent
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            RendezVous saveRDV = hospitalService.saveRv(rendezVous);
            System.out.println(saveRDV.getId());


            //Creation d'une consultation pour un rdv
            RendezVous rendezVous1 = rendezVousRepository.findAll().get(0);
            Consultation consultation = new Consultation();
            consultation.setDateC(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("Rapport de la consultation .....");
            hospitalService.saveC(consultation);
        };

    }
}












