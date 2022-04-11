package ma.enset.s4jpahospital.web;

import ma.enset.s4jpahospital.entities.Patient;
import ma.enset.s4jpahospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {

    //L'injection de depandence avec Autowired
    @Autowired
    private PatientRepository patientRepository;

    //Consulter la liste des patients
    @GetMapping("/patients")
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }
}













