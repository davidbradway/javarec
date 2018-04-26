/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatingsByYear() {
        //ThirdRatings tr = new ThirdRatings ("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings ("ratings.csv");

        // Print the number of raters 
        System.out.println("Number of raters read in: " + tr.getRaterSize());
        // For example, if you run your program on the files ratings_short.csv,
        // you should see 5 raters.

        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        // Print a list of movies and their average ratings, for all those movies that have at
        // least a specified number of ratings, sorted by averages.
        //ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        System.out.println("Found " + avgRatings.size() + " movies");
  
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) +" "+ MovieDatabase.getTitle(r.getItem()));
        }
        // For example, if you run the printAverageRatingsByYear method on the files 
        // ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1 and the year 
        // 2000, you should see:
        // read data for 5 raters
        // read data for 5 movies
        // found 2 movies
        // 7.0 2013 Dallas Buyers Club
        // 8.25 2013 Her
    }

    public void printAverageRatingsByGenre() {
        //ThirdRatings tr = new ThirdRatings ("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings ("ratings.csv");
        System.out.println("Number of raters read in: " + tr.getRaterSize());

        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        //ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
        System.out.println("Found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
        // you should see:
        // read data for 5 raters
        // read data for 5 movies
        // found 2 movies
        // 9.0 The Godfather
        // Crime, Drama
        // 10.0 Heat
        // Action, Crime, Drama
    }

    public void printAverageRatingsByMinutes() {
        //ThirdRatings tr = new ThirdRatings ("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings ("ratings.csv");
        System.out.println("Number of raters read in: " + tr.getRaterSize());

        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());
        
        //ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(1, new MinutesFilter(?));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        System.out.println("Found " + avgRatings.size() + " movies");
  
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
        //    System.out.println(r.getValue() + " Time: " + 
        //                       MovieDatabase.getMinutes(r.getItem()) + " " + 
        //                       MovieDatabase.getTitle(r.getItem()));
        }
        // you should see:
        // read data for 5 raters
        // read data for 5 movies
        // found 3 movies
        // 7.0 Time: 117 Dallas Buyers Club
        // 8.25 Time: 126 Her
        // 10.0 Time: 170 Heat
    } 
    
    public void printAverageRatingsByDirectors () {
        //ThirdRatings tr = new ThirdRatings ("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings ("ratings.csv");
        System.out.println("Number of raters read in: " + tr.getRaterSize());

        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());
        
        //ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(1, new 
        //                         DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(4, new 
           DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println("Found " + avgRatings.size() + " movies");
  
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) +
                               "\n" + MovieDatabase.getDirector(r.getItem()));
        }
        // you should see:
        // read data for 5 raters
        // read data for 5 movies
        // found 2 movies
        // 8.25 Her
        // Spike Jonze
        // 10.0 Heat
        // Michael Mann
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        //ThirdRatings tr = new ThirdRatings ("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings ("ratings.csv");
        System.out.println("Number of raters read in: " + tr.getRaterSize());

        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        //af.addFilter(new YearAfterFilter(1980));
        af.addFilter(new YearAfterFilter(1990));
        //af.addFilter(new GenreFilter("Romance"));
        af.addFilter(new GenreFilter("Drama"));

        //ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(1, af);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(8, af);
        System.out.println("Found " + avgRatings.size() + " movies");
  
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            //System.out.println(r.getValue() + " Year: " + MovieDatabase.getYear(r.getItem()) 
            //                   + " " + MovieDatabase.getTitle(r.getItem()) +
            //                   "\n" + MovieDatabase.getGenres(r.getItem()));
        }
        // you should see:
        // read data for 5 raters
        // read data for 5 movies
        // 1 movie matched
        // 8.25 2013 Her
        // Drama, Romance, Sci-Fi        
    }

    public void printAverageRatingsByDirectorsAndMinutes () {
        //ThirdRatings tr = new ThirdRatings ("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings ("ratings.csv");
        System.out.println("Number of raters read in: " + tr.getRaterSize());

        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        AllFilters af = new AllFilters();
        //af.addFilter(new MinutesFilter(30, 170));
        af.addFilter(new MinutesFilter(90, 180));
        //af.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));

        //ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(1, af);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(3, af);
        System.out.println("Found " + avgRatings.size() + " movies");

        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) 
                               + " " + MovieDatabase.getTitle(r.getItem()) +
                               "\n" + MovieDatabase.getDirector(r.getItem()));
        }
        // you should see:
        // read data for 5 raters
        // read data for 5 movies
        // 2 movies matched
        // 8.25 Time: 126 Her
        // Spike Jonze
        // 10.0 Time: 170 Heat
        // Michael Mann
    }

    public void printAverageRatings() {
        //ThirdRatings tr = new ThirdRatings ("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings ("ratings.csv");

        // Print the number of raters 
        System.out.println("Number of raters read in: " + tr.getRaterSize());
        // For example, if you run your program on the files ratings_short.csv,
        // you should see 5 raters.

        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        // Print a list of movies and their average ratings, for all those movies that have at
        // least a specified number of ratings, sorted by averages.
        //ArrayList<Rating> avgRatings = tr.getAverageRatings(1);
        ArrayList<Rating> avgRatings = tr.getAverageRatings(35);
        System.out.println("Found " + avgRatings.size() + " movies");

        // Specifically, this method should print the list of movies, one movie per
        // line (print its rating first, followed by its title) in sorted order by ratings,
        // lowest rating to highest rating.        
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " "+ MovieDatabase.getTitle(r.getItem()));
        }

        // For example, if printAverageRatings is called on the files ratings_short.csv and
        // ratedmovies_short.csv with the argument 1, then the output will display:
        // 7.0 Dallas Buyers Club
        // 8.25 Her
        // 9.0 The Godfather
        // 10.0 Heat
    }
}
