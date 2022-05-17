package ma.enset.s9etudiantsmvc.sec.repositories;

import ma.enset.s9etudiantsmvc.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}



























