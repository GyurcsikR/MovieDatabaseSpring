package progmatic.moviedatabase.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import progmatic.moviedatabase.Model.Movie;
import progmatic.moviedatabase.Repository.MovieRepository;
import progmatic.moviedatabase.SearchForm.MovieSearchForm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }



    public List<Movie> loadMovies() throws IOException{
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/progmatic/moviedatabase/Service/FilmAdatbázisFormázott.csv"))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    Movie movie = new Movie(line);
                    movies.add(movie);
                } catch (Exception e) {
                }
            }
        }
        movieRepository.saveAll(movies);
        return movies;

    }
    public Long countMovies(){
        return movieRepository.count();
    }

    public List<Movie> getAll() {
        return new ArrayList<>((Collection) movieRepository.findAll());
    }

    public List<Movie> getByForm(MovieSearchForm form) {
        List<Movie> result = new ArrayList<>();

        for (Movie movie : getAll()) {
            if (form.getId() != null && !form.getId().equals(movie.getId())) {
                continue;
            }

            if (form.getTitle() != null && !movie.getTitle().contains(form.getTitle())) {
                continue;
            }

            if (form.getMadeYear() != 0 && movie.getMadeYear() != form.getMadeYear()) {
                continue;
            }

            if (form.getProducer() != null && !movie.getProducer().contains(form.getProducer())) {
                continue;
            }

            if (form.getActor() != null && !movie.getActor().contains(form.getActor())) {
                continue;
            }

            result.add(movie);
        }

        return result;
    }




}
