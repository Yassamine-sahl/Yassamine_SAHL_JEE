package ma.enset.s5jpauserroles.service;

import ma.enset.s5jpauserroles.entities.Role;
import ma.enset.s5jpauserroles.entities.User;

public interface UserService {
    User addNewUser(User user);

    Role addNewRole(Role role);

    User findUserByUserName(String userName);

    Role findRoleByRoleName(String roleName);

    void addRoletoUser(String userName, String roleName);

    User authenticate(String username, String password);
}
