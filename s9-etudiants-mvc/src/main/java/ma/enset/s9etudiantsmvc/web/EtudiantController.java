package ma.enset.s9etudiantsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.s9etudiantsmvc.entities.Etudiant;
import ma.enset.s9etudiantsmvc.repository.EtudiantRepository;
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
//l'injection en utilisant l'annotation AllArgsConstructor
@AllArgsConstructor
public class EtudiantController {

    private EtudiantRepository etudiantRepository;

    @GetMapping("/index")
    public String etudiant (Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "5") int size,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword){

        /* Afficher la liste des etudiants
         List<Etudiant> etudiants = etudiantRepository.findAll();
         model.addAttribute("listEtudiants", etudiants);
         */

        //Afficher 5 etudiants par Page (La pagination)
        Page<Etudiant> pageEtudiants = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listEtudiants", pageEtudiants.getContent());

        //Afficher la page currente
        model.addAttribute("currentPage", page);

        //Ajouter un etudiant
        model.addAttribute("pages", new int[pageEtudiants.getTotalPages()]);

        //Afficher la valeur actuelle de keyword
        model.addAttribute("keyword", keyword);

        return "etudiants";
    }

    //Supprimer un etudiant
    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        etudiantRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    //Page home
    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    //Pour serialiser la liste dans le corps de la reponse
    //Affiche la liste des patients en format JSON
    @GetMapping("/etudiants")
    @ResponseBody
    public List<Etudiant> etudiantList() {
        return etudiantRepository.findAll();
    }

    @GetMapping("/formEtudiants")
    public String formEtudiants(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiants";
    }

    @PostMapping(path = "/save")
    public String save(Model model, @Valid Etudiant etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formEtudiants";
        etudiantRepository.save(etudiant);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editEtudiant")
    public String editEtudiant(Model model, Long id, String keyword, int page){
        Etudiant etudiant=etudiantRepository.findById(id).orElse(null);
        if (etudiant==null) throw new RuntimeException("Etudiant introuvable");
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editEtudiant";
    }
}






















