package progmatic.moviedatabase.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String originalTitle;
    private String genre;
    private int madeYear;
    private String manufacturer;
    private String producer;
    private String actor;
    private String ageLimit;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public Movie() {
    }

    public Movie(String title, String originalTitle, String genre, int madeYear, String manufacturer, String producer, String actor, String ageLimit) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.genre = genre;
        this.madeYear = madeYear;
        this.manufacturer = manufacturer;
        this.producer = producer;
        this.actor = actor;
        this.ageLimit = ageLimit;
    }
    public Movie(String line){
        String[] parts = line.split(";");
        this.title = parts[0];
        this.originalTitle = parts[1];
        this.genre = parts[2];
        this.madeYear = Integer.parseInt(parts[3]);
        this.manufacturer = parts[4];
        this.producer = parts[5];
        this.actor = parts[6];
        this.ageLimit = parts[7];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getMadeYear() {
        return madeYear;
    }

    public void setMadeYear(int madeYear) {
        this.madeYear = madeYear;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", madeYear=" + madeYear +
                ", manufacturer='" + manufacturer + '\'' +
                ", producer='" + producer + '\'' +
                ", actor='" + actor + '\'' +
                ", ageLimit='" + ageLimit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
