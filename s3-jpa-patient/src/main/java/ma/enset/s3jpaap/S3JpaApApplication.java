package ma.enset.s3jpaap;

import ma.enset.s3jpaap.entities.Patient;
import ma.enset.s3jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class S3JpaApApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(S3JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            patientRepository.save(
                    new Patient(null, "Salma", new Date(2000 - 07 - 3), Math.random() > 0.5 ? false : true, (int) (Math.random() * 100)));
        }
        // Object Page affiche la page 0 et les 5 premiers elements
        Page<Patient> patientList = patientRepository.findAll(PageRequest.of(0, 5));

        //Afficher le nombre total des pages
        System.out.println("Total des pages: " + patientList.getTotalPages());

        //Afficher les elements de la page
        System.out.println("Total elements :" + patientList.getTotalElements());

        //Afficher le numero de la page
        System.out.println("Num page :" + patientList.getNumber());

        //Afficher la liste des patients (contenu)
        List<Patient> content = patientList.getContent();

        //Notre fonction findbyMalade pour recuperer la liste des patients malade
        List<Patient> byMalade = patientRepository.findByMalade(true);

        //La pagination : recuperer les 4 patients dans la page 0
        Page<Patient> byMaladePage = patientRepository.findByMalade(true, PageRequest.of(0, 4));

        //Tester la methode chercherPatient que nous avons creer
        List<Patient> patientList1 = patientRepository.cherhcerPatients("%S%", 40);
        patientList1.forEach(patient -> {
            System.out.println("=============================");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getScore());
            System.out.println(patient.getDateN());
            System.out.println(patient.isMalade());
        });
        System.out.println("+++++++++++++++++++");

        Patient patient = patientRepository.findById(1L).orElse(null);
        if (patient != null) {
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }
        patient.setScore(870);
        patientRepository.save(patient);
        patientRepository.deleteById(1L);
    }
}
