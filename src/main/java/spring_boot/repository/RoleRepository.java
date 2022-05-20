package spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
