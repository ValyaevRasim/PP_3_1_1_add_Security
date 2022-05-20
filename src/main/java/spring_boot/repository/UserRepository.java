package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

    User findUserById(Long id);
}
