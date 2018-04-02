/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        // The constructor should create a FirstRatings object
        FirstRatings fr = new FirstRatings();
        // and then call the loadMovies and loadRaters methods in FirstRatings to read in all
        // the movie and ratings data and store them in the two private ArrayList variables of
        // the SecondRatings class, myMovies and myRaters.
        myMovies = fr.loadMovies("data/" + moviefile);
        myRaters = fr.loadRaters("data/" + ratingsfile);
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        // return double representing the average movie rating for this ID if there are at
        // least minimalRaters ratings.
        // If there are not minimalRaters ratings, then it returns 0.0.
        int raters = 0;
        double total = 0.0;
        
        for (Rater r : myRaters) {
            if (r.hasRating(id)) {
                total += r.getRating(id);
                raters++;
            }
        }
        if (raters >= minimalRaters) {
            return total/raters;
        }
        else {
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        // find the average rating for every movie rated by at least minimalRaters raters.
        // The method getAverageRatings should return an ArrayList of all the Rating objects
        // for movies that have at least the minimal number of raters supplying a rating.
        
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            double avg = getAverageByID(m.getID(), minimalRaters);
            
            // can't tell difference between a 0.0 rating and movie with no or few ratings
            if (avg > 0.0) {
                avgRatings.add(new Rating(m.getID(), avg));
            }
        }
        return avgRatings;
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
    
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "ID was not found.";
    } 
    
    public int getMovieSize() {
        // return number of movies read in and stored in the ArrayList of type Movie.
        return myMovies.size();
    }
        
    public int getRaterSize() {
        // return number of movies read in and stored in the ArrayList of type Movie.
        return myRaters.size();
    }
}
