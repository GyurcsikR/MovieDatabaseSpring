package progmatic.moviedatabase.Repository;


import org.springframework.data.repository.CrudRepository;
import progmatic.moviedatabase.Model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
