/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        // The constructor should create a FirstRatings object
        FirstRatings fr = new FirstRatings();
        // and then call the loadRaters method in FirstRatings to read in all
        // the ratings data and store them in the private ArrayList variable 
        // the ThirdRatings class, myRaters.

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
        
        /**
         * get all the movies from the MovieDatabase class and store them in an ArrayList 
         * of movie IDs. 
         * Thus, you will need to modify getAverageRatings to call MovieDatabase with a 
         * filter, and in this case you can use the TrueFilter to get every movie.
         */
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for (String m : movies) {
            double avg = getAverageByID(m, minimalRaters);
            
            // can't tell difference between a 0.0 rating and movie with no or few ratings
            if (avg > 0.0) {
                avgRatings.add(new Rating(m, avg));
            }
        }
        return avgRatings;
    }
            
    public int getRaterSize() {
        // return number of movies read in and stored in the ArrayList of type Movie.
        return myRaters.size();
    }
}
