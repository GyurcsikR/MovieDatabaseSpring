package progmatic.moviedatabase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import progmatic.moviedatabase.Model.Movie;
import progmatic.moviedatabase.Repository.MovieRepository;
import progmatic.moviedatabase.SearchForm.MovieSearchForm;
import progmatic.moviedatabase.Service.MovieService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    private MovieService movieService;
    private MovieRepository movieRepository;

    @Autowired
    public MainController(MovieService movieService, MovieRepository movieRepository){
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @GetMapping(value = "/")
    public String getMoviesListSize(Model model) throws IOException {
        if(movieService.countMovies() == 0) {
            List<Movie> movies = movieService.loadMovies();
        }
        model.addAttribute("listSize", movieService.getAll().size() + " film");

        return "index";
    }

    @GetMapping("/list")
    public String listAllMovies(Model model) throws IOException {
        model.addAttribute("movies", movieRepository.findAllByIdBetween(1L, 100L));
        return "list.html";
    }

    @GetMapping(value = "/list/search")
    public String searchMovie(Model model) {
        List<Movie> movies = movieRepository.findAllByIdBetween(1L, 100L);
        model.addAttribute("form", new MovieSearchForm());
        model.addAttribute("movies", movies);

        return "searchMovie";
    }

    @PostMapping("/list/search")
    public String displaySearchResults(MovieSearchForm form, Model model) {
        List<Movie> movies = movieService.getByForm(form);
        model.addAttribute("form", form);
        model.addAttribute("movies", movies);

        return "searchMovie";
    }
}

