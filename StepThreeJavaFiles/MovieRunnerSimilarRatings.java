/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {

    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings("ratings.csv");
        System.out.println("Number of raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        //ArrayList<Rating> simRatings = fr.getSimilarRatings("65", 20, 5);
        //ArrayList<Rating> simRatings = fr.getSimilarRatings("337", 10, 3);
        ArrayList<Rating> simRatings = fr.getSimilarRatings("71", 20, 5);
        System.out.println("Found " + simRatings.size() + " movies");

        for (Rating r : simRatings) {
            System.out.println(r.getItem()+ " " + r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("Best: " + simRatings.get(0).getItem()+ " " + 
                           simRatings.get(0).getValue() + " " +
                           MovieDatabase.getTitle(simRatings.get(0).getItem()));
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        System.out.println("Number of raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        //ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("65", 20, 5, 
        //                                   new GenreFilter("Action"));
        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("964", 20, 5, 
                                           new GenreFilter("Mystery"));
        System.out.println("Found " + simRatings.size() + " movies");

        for (Rating r : simRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) +
                               "\n" + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("Best: " + simRatings.get(0).getItem()+ " " + 
                           simRatings.get(0).getValue() + " " +
                           MovieDatabase.getTitle(simRatings.get(0).getItem()));

    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        System.out.println("Number of raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        //ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("1034", 10, 3, 
        //  new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("120", 10, 2,
          new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        System.out.println("Found " + simRatings.size() + " movies");

        for (Rating r : simRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) +
                               "\n" + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println("Best: " + MovieDatabase.getTitle(simRatings.get(0).getItem()));
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        System.out.println("Number of raters read in: " + RaterDatabase.size());        
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        AllFilters af = new AllFilters();
        //af.addFilter(new GenreFilter("Adventure"));
        //af.addFilter(new MinutesFilter(100, 200));
        af.addFilter(new GenreFilter("Drama"));
        af.addFilter(new MinutesFilter(60, 160));
        
        //ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("65", 10, 5, af);
        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("168", 10, 3, af);
        System.out.println("Found " + simRatings.size() + " movies");

        for (Rating r : simRatings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + 
                               MovieDatabase.getMinutes(r.getItem()) + "min " +
                               r.getValue() + "\n" + 
                               MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("Best: " + MovieDatabase.getTitle(simRatings.get(0).getItem()));
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        System.out.println("Number of raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        AllFilters af = new AllFilters();
        //af.addFilter(new YearAfterFilter(2000));
        //af.addFilter(new MinutesFilter(80, 100));
        af.addFilter(new YearAfterFilter(1975));
        af.addFilter(new MinutesFilter(70, 200));

        //ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("65", 10, 5, af);
        ArrayList<Rating> simRatings = fr.getSimilarRatingsByFilter("314", 10, 5, af);
        System.out.println("Found " + simRatings.size() + " movies");

        for (Rating r : simRatings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + 
                               MovieDatabase.getYear(r.getItem()) + " " +
                               MovieDatabase.getMinutes(r.getItem()) + "min " +
                               r.getValue());
        }
        System.out.println("Best: " + simRatings.get(0).getItem()+ " " + 
                           simRatings.get(0).getValue() + " " +
                           MovieDatabase.getTitle(simRatings.get(0).getItem()));
    }
    
    public void printAverageRatings() {
        FourthRatings fr = new FourthRatings("ratings_short.csv");
        //FourthRatings fr = new FourthRatings("ratings.csv");

        // Print the number of raters 
        System.out.println("Number of raters read in: " + RaterDatabase.size());
        // For example, if you run your program on the files ratings_short.csv,
        // you should see 5 raters.

        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());

        // Print a list of movies and their average ratings, for all those movies that have at
        // least a specified number of ratings, sorted by averages.
        ArrayList<Rating> avgRatings = fr.getAverageRatings(1);
        //ArrayList<Rating> avgRatings = fr.getAverageRatings(35);
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
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = new FourthRatings("ratings_short.csv");
        //FourthRatings fr = new FourthRatings ("ratings.csv");
        System.out.println("Number of raters read in: " + RaterDatabase.size());

        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies read in: " + MovieDatabase.size());
        
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1980));
        //af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Romance"));
        //af.addFilter(new GenreFilter("Drama"));

        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(1, af);
        //ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(8, af);
        System.out.println("Found " + avgRatings.size() + " movies");
  
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " Year: " + MovieDatabase.getYear(r.getItem()) 
                               + " " + MovieDatabase.getTitle(r.getItem()) +
                               "\n" + MovieDatabase.getGenres(r.getItem()));
        }
        // you should see:
        // read data for 5 raters
        // read data for 5 movies
        // 1 movie matched
        // 8.25 2013 Her
        // Drama, Romance, Sci-Fi        
    }
}
