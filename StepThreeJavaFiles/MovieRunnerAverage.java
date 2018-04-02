/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        // Create a SecondRatings object and use the CSV filenames of movie information and
        // ratings information from the first assignment when calling the constructor.
        SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");

        //- Print the number of movies and number of raters from the two files by calling the
        // appropriate methods in the SecondRatings class.
        // Test your program to make sure it is reading in all the data from the two files.
        System.out.println("Number of movies read in: " + sr.getMovieSize());
        System.out.println("Number of raters read in: " + sr.getRaterSize());
        // For example, if you run your program on the files ratings_short.csv and
        // ratedmovies_short.csv, you should see 5 raters and 5 movies.
        
        // Print a list of movies and their average ratings, for all those movies that have at
        // least a specified number of ratings, sorted by averages.
        
        // Specifically, this method should print the list of movies, one movie per
        // line (print its rating first, followed by its title) in sorted order by ratings,
        // lowest rating to highest rating.        
        ArrayList<Rating> avgRatings = sr.getAverageRatings(3);
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(sr.getTitle(r.getItem()) + " "+ r.getValue());
        }
        
        // For example, if printAverageRatings is called on the files ratings_short.csv and
        // ratedmovies_short.csv with the argument 3, then the output will display two movies:
        // 8.25 Her
        // 9.0 The Godfather
        
        sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        avgRatings = sr.getAverageRatings(50);
        System.out.println("50 or more: " + avgRatings.size());
        
        avgRatings = sr.getAverageRatings(12);
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(sr.getTitle(r.getItem()) + " "+ r.getValue());
        }    
    }
    
    public void getAverageRatingOneMovie() {
        //SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        ArrayList<Rating> avgRatings = sr.getAverageRatings(1);

        // Print out the average ratings for a specific movie title, such as the movie “The 
        // Godfather”. If the moviefile is set to the file named ratedmovies_short.csv, and
        // the ratingsfile is set to the file ratings_short.csv, then the average for the
        // movie “The Godfather” would be 9.0.
        //String id = sr.getID("The Godfather");
        //String id = sr.getID("The Maze Runner");
        //String id = sr.getID("Moneyball");
        String id = sr.getID("Vacation");
        for (Rating r : avgRatings) {
            if (r.getItem().equals(id)) {
                System.out.println(sr.getTitle(r.getItem()) + " "+ r.getValue());
                break;
            }
        }
    }
}
