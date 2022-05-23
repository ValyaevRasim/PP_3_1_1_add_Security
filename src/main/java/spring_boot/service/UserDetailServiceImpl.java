package spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_boot.entity.Role;
import spring_boot.repository.UserRepository;
import spring_boot.entity.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserDetailServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserDetailServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

//
//    @Bean
//    private BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
