package pfa.project.ForumConnect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pfa.project.ForumConnect.model.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<user, Long>{
    Optional<user> findByMail(String mail);
}
