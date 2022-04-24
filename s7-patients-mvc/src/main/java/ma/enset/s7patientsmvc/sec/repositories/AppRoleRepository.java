package ma.enset.s7patientsmvc.sec.repositories;

import ma.enset.s7patientsmvc.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName (String roleName);
}
