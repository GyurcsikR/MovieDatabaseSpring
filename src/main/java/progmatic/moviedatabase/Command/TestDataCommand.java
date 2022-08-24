package progmatic.moviedatabase.Command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import progmatic.moviedatabase.Model.User;
import progmatic.moviedatabase.Repository.MovieRepository;
import progmatic.moviedatabase.Repository.UserRepository;

@Component
public class TestDataCommand implements CommandLineRunner {
    private final MovieRepository movieRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public TestDataCommand(
            MovieRepository movieRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Generating test users...");

        userRepository.save(new User("user", passwordEncoder.encode("password")));
        System.out.println("User user generated.");

        userRepository.save(new User("admin", passwordEncoder.encode("password"), true));
        System.out.println("Admin user generated.");
    }
}
