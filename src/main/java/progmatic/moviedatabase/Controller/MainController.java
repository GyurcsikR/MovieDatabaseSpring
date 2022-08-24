package progmatic.moviedatabase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import progmatic.moviedatabase.Model.Movie;
import progmatic.moviedatabase.Repository.MovieRepository;
import progmatic.moviedatabase.SearchForm.MovieSearchForm;
import progmatic.moviedatabase.Service.DatabaseUserDetailsService;
import progmatic.moviedatabase.Service.MovieService;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    private MovieService movieService;
    private MovieRepository movieRepository;

    private DatabaseUserDetailsService databaseUserDetailsService;

    @Autowired
    public MainController(MovieService movieService, MovieRepository movieRepository, DatabaseUserDetailsService databaseUserDetailsService){
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.databaseUserDetailsService = databaseUserDetailsService;
    }

    @GetMapping(value = "/")
    public String getMoviesListSize(Model model) throws IOException {
        //System.out.println(pageUserDetailsService.getLoggedInUser());
        if(movieService.countMovies() == 0) {
            List<Movie> movies = movieService.loadMovies();
        }
        model.addAttribute("listSize", movieService.getAllMovie().size() + " film");

        return "index";
    }

    @GetMapping("/list")
    public String  getAllMovie(Model model){
        return listByPage(model, 1);
    }

    @GetMapping("page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage){

        Page<Movie> page = movieService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Movie> listMovies = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("movies", listMovies);
        return "list";

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

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginErrorPage(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession(true).invalidate();

        return "login";
    }
}

