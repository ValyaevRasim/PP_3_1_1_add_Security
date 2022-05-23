package spring_boot.service;

import spring_boot.entity.Role;
import spring_boot.entity.User;

import java.util.List;

public interface RoleService {
    public void saveRole(Role role);
    public void deleteRoleById(long id);
    public Role getRoleById (long id);
    public List<Role> getAllRoles();
    public Role findRoleByName(String roleName);
}
