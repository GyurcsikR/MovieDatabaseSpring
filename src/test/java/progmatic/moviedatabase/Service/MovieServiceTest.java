package progmatic.moviedatabase.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import progmatic.moviedatabase.Model.Movie;
import progmatic.moviedatabase.Repository.MovieRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getMoviesTest() {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie("Forrest Gump", "Forrest Gump", "filmszatíra", 1994, "amerikai"
                , "Robert Zemeckis", "Tom Hanks/Sally Field/Gary Sinise","12 éves kor alatt nem ajánlott");
        movies.add(movie);

        when(movieRepository.findAll()).thenReturn(movies);
        assertEquals(1, movieService.getAllMovie().size());
    }

}