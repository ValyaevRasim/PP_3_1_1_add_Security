package spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot.entity.Role;
import spring_boot.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listRoles(){
        return roleRepository.findAll();
    };

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
}
