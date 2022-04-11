package ma.enset.s4jpahospital.service;

import ma.enset.s4jpahospital.entities.Consultation;
import ma.enset.s4jpahospital.entities.Medecin;
import ma.enset.s4jpahospital.entities.Patient;
import ma.enset.s4jpahospital.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);

    Medecin saveMedecin(Medecin medecin);

    RendezVous saveRv(RendezVous rendezVous);

    Consultation saveC(Consultation consultation);
}


