package progmatic.moviedatabase.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import progmatic.moviedatabase.Model.User;
import progmatic.moviedatabase.Repository.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DatabaseUserDetailsServiceTest {

    @InjectMocks
    private DatabaseUserDetailsService databaseUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userSavingTest(){
        User user = new User(999L, "Ricsi", "alina", true);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, databaseUserDetailsService.saveUser(user));
    }

  /*  @Test
    public void getUserByUsername(){
        String username = "Ricsi";
        Optional<User> value = (Optional<User>) Optional.of(username);
    }*/



}