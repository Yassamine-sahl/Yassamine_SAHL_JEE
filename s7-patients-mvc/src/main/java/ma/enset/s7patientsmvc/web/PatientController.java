package ma.enset.s7patientsmvc.web;

import lombok.AllArgsConstructor;


import ma.enset.s7patientsmvc.entities.Patient;
import ma.enset.s7patientsmvc.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
//l'injection en utilisant l'annotation AllArgsConstructor pour ajouter le constructeur avec parametre
@AllArgsConstructor
public class PatientController {

    //l'injection via l'annotation
    //@Autowired
    private PatientRepository patientRepository;
    // l'injection via le constructeur
    /*public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }*/

    @GetMapping(path = "/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        //Afficher la liste des patients
        //List<Patient> patients = patientRepository.findAll();
        // model.addAttribute("listPatients", patients);

        //Afficher 5 patients par Page (La pagination)
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());

        //Afficher la page currente
        model.addAttribute("currentPage", page);

        //Ajouter un patient
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);

        //Afficher la valeur actuelle de keyword
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/user/patients")
    //Pour serialiser la liste dans le corps de la reponse
    //Affiche la liste des patients en format JSON
    @ResponseBody
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @GetMapping("/admin/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/admin/save")
    public String save(Model model,
                       @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page"+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }

}

































