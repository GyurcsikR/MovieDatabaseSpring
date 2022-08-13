package progmatic.moviedatabase.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import progmatic.moviedatabase.Model.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {


}
