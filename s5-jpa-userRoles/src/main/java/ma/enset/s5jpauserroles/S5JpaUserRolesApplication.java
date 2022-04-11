package ma.enset.s5jpauserroles;

import ma.enset.s5jpauserroles.entities.Role;
import ma.enset.s5jpauserroles.entities.User;
import ma.enset.s5jpauserroles.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class S5JpaUserRolesApplication {

    public static void main(String[] args) {
        SpringApplication.run(S5JpaUserRolesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            User user = new User();
            user.setUserName("user1");
            user.setPassword("123456");
            userService.addNewUser(user);

            User user2 = new User();
            user2.setUserName("admin");
            user2.setPassword("123456");
            userService.addNewUser(user2);

            Stream.of("STUDENT", "USER", "ADMIN").forEach(r -> {
                Role role = new Role();
                role.setRoleName(r);
                userService.addNewRole(role);
            });

            userService.addRoletoUser("user1", "STUDENT");
            userService.addRoletoUser("user1", "USER");
            userService.addRoletoUser("admin", "USER");
            userService.addRoletoUser("admin", "ADMIN");

            try {
                User u = userService.authenticate("user1", "123456");
                System.out.println(u.getUserId());
                System.out.println(u.getUserName());
                u.getRoles().forEach(r -> {
                    System.out.println("Roles ==>" + r.toString());
                });
            } catch (Exception e) {
                //Exception :BAD credentials => ce qu'il veut dire que le password n'existe pas
                e.printStackTrace();
            }
        };

    }

}
