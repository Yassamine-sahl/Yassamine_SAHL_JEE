package ma.enset.s5jpauserroles.service;

import lombok.AllArgsConstructor;
import ma.enset.s5jpauserroles.entities.Role;
import ma.enset.s5jpauserroles.entities.User;
import ma.enset.s5jpauserroles.repositories.RoleRepository;
import ma.enset.s5jpauserroles.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoletoUser(String userName, String roleName) {
        User user = findUserByUserName(userName);
        Role role = findRoleByRoleName(roleName);
        if (user.getRoles() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        //userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUserName(username);
        if (user == null) throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
