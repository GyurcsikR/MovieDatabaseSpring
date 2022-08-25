package progmatic.moviedatabase.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.moviedatabase.Model.User;
import progmatic.moviedatabase.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User userData = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));


        if (userData.isAdmin()) {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER"),
                            new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            );
        } else {
            return new org.springframework.security.core.userdetails.User(
                    userData.getUsername(),
                    userData.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority("ROLE_USER")
                    )
            );
        }
    }
    public UserDetails getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails user) {
            return user;
        }

        return null;
    }
    @Transactional
    public void saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
