package ma.enset.s7patientsmvc.sec.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.enset.s7patientsmvc.sec.entities.AppRole;
import ma.enset.s7patientsmvc.sec.entities.AppUser;
import ma.enset.s7patientsmvc.sec.repositories.AppRoleRepository;
import ma.enset.s7patientsmvc.sec.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
//Permet de donner un attribut qui s'appelle Log qui permet de login
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String Username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("Password not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(Username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole =appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw new RuntimeException("Role "+roleName+" already exist");
        appRole= new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole =appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser= appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User not found ");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw new RuntimeException("Role not found ");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser= appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User not found ");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if (appRole==null) throw new RuntimeException("Role not found ");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
