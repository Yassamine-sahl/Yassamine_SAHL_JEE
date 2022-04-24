package ma.enset.s7patientsmvc.sec.service;

import ma.enset.s7patientsmvc.sec.entities.AppRole;
import ma.enset.s7patientsmvc.sec.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String Username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}
