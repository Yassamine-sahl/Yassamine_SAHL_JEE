package ma.enset.s4jpahospital.repositories;

import ma.enset.s4jpahospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNom(String name);
}


