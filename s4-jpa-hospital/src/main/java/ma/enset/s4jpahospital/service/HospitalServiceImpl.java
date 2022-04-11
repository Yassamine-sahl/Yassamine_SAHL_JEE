package ma.enset.s4jpahospital.service;

import ma.enset.s4jpahospital.entities.Consultation;
import ma.enset.s4jpahospital.entities.Medecin;
import ma.enset.s4jpahospital.entities.Patient;
import ma.enset.s4jpahospital.entities.RendezVous;
import ma.enset.s4jpahospital.repositories.ConsultationRepository;
import ma.enset.s4jpahospital.repositories.MedecinRepository;
import ma.enset.s4jpahospital.repositories.PatientRepository;
import ma.enset.s4jpahospital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {

    //Declaration des interfaces
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    //Injection des dependances en utilisant le constructeur avec parametre
    public HospitalServiceImpl(PatientRepository patientRepository,
                               MedecinRepository medecinRepository,
                               RendezVousRepository rendezVousRepository,
                               ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRv(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveC(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}













