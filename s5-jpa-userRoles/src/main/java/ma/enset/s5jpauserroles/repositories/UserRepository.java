package ma.enset.s5jpauserroles.repositories;

import ma.enset.s5jpauserroles.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
