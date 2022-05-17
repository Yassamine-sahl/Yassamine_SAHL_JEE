package ma.enset.s9etudiantsmvc.sec.repositories;

import ma.enset.s9etudiantsmvc.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName (String roleName);
}
























