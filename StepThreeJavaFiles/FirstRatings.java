import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    /**
     * loadMovies
     */
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource(filename);
        
        ArrayList<Movie> movielist = new ArrayList<Movie>();
        //id,title,year,country,genre,director,minutes,poster
        for (CSVRecord rec : fr.getCSVParser(true)) {
            String id = rec.get(0);
            String title = rec.get(1);
            //int year = Integer.parseInt(rec.get(2));
            String year = rec.get(2);
            String country = rec.get(3);
            String genres = rec.get(4);
            String director = rec.get(5);
            int minutes = Integer.parseInt(rec.get(6));
            //String minutes = rec.get(6);
            String poster = rec.get(7);
            movielist.add(new Movie(id, title, year, genres, director, country, poster, minutes));
        }
        
        return movielist;
    }
    
    public ArrayList<Movie> filterByGenre(ArrayList<Movie> movielist, String genre) {

        ArrayList<Movie> filteredlist = new ArrayList<Movie>();
        for (Movie m : movielist) {
            // process each item in turn
            String genres = m.getGenres();
            if (genres.contains(genre)){
                //
                filteredlist.add(m);
            }
        }
        return filteredlist;
    }

    public ArrayList<Movie> filterByLengthLonger(ArrayList<Movie> movielist, int minlength) {

        ArrayList<Movie> filteredlist = new ArrayList<Movie>();
        for (Movie m : movielist) {
            // process each item in turn
            int minutes = m.getMinutes();
            if (minutes > minlength){
                //
                filteredlist.add(m);
            }
        }
        return filteredlist;
    }

    /**
     * find maximum number of movies by any director, 
     * and who the directors are that directed that many movies. 
     * Remember that some movies may have more than one director.     
     */
    public HashMap<Integer, ArrayList<String>> countDirectors(ArrayList<Movie> movielist){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        int maxcount = 0;
        ArrayList<String> directorsmax = new ArrayList<String>();
        
        // iterate through entire movielist
        for (Movie m : movielist) {
            // get Directors
            String myDirectors = m.getDirector();

            // Split directors String on commas
            String directorarray[] = myDirectors.split(",");
            for (String director: directorarray){
                int count = 0;

                // for each director check if HashMap containsKey
                if (map.containsKey(director)) {
                    // If it does,
                    // get the object, and overwrite the count value
                    count = map.get(director);
                }
                else {
                    // Director wasn't in the list
                    // keep the count as initialized at zero
                }
                //increment the count
                count++;
                // save it to the HashMap
                map.put(director, count);
                
                // compare count to maxcount 
                if (count == maxcount){
                    // add to ArrayList<String> of directors with that maxcount
                    directorsmax.add(director);
                }
                else if (count > maxcount) {
                    // found a new max
                    maxcount = count;
                    // reset the ArrayList
                    directorsmax.clear();
                    // add director to ArrayList<String> 
                    directorsmax.add(director);
                }
            }
        }        

        HashMap<Integer, ArrayList<String>> maxMap = new HashMap<Integer, ArrayList<String>>();
        maxMap.put(maxcount, directorsmax);
        return maxMap;
    }
    
    /**
     * testLoadMovies 
     */
    public void testLoadMovies() {
        
        //ArrayList<Movie> movielist = loadMovies("data/ratedmovies_short.csv"); //5
        ArrayList<Movie> movielist = loadMovies("data/ratedmoviesfull.csv"); //3143

        System.out.println("Number of Movies: " + movielist.size());
        for (Movie m : movielist) {
            // process each item in turn 
            //System.out.println(m);
        }
        // You should see there are five movies in this file, which are all shown above. 
        // After this works you should comment out the printing of the movies.

        ArrayList<Movie> filteredlist = filterByGenre(movielist, "Comedy");
        System.out.println("Number of Comedies: " + filteredlist.size());
        //In the file ratedmovies_short.csv, there is only one.
        
        filteredlist = filterByLengthLonger(movielist, 150);
        System.out.println("Number of Movies over 150 min: " + filteredlist.size());
        //In the file ratedmovies_short.csv, there are two.
        
        HashMap<Integer, ArrayList<String>> maxMap = countDirectors(movielist);
        for (Integer num : maxMap.keySet()) {
            System.out.println("Max Number of movies by any given director: "+ num);
            ArrayList<String> directors = maxMap.get(num);
            System.out.println("Number of directors who have directed that many movies: " + directors.size());
            System.out.println("Names of directors who have directed that many movies: " + directors);
        }
        /* 
         * In the file ratedmovies_short.csv 
         * the maximum number of movies by any director is one, 
         * and there are five directors that directed one such movie.
         */
    }
    
    /**
     * loadRaters
     */
    public ArrayList<Rater> loadRaters(String filename) {
        //process every record from the CSV file whose name is filename,

        FileResource fr = new FileResource(filename);
        
        ArrayList<Rater> raterlist = new ArrayList<Rater>();
        //rater_id,movie_id,rating,time
        for (CSVRecord rec : fr.getCSVParser(true)) {
            String rater_id = rec.get(0);
            String movie_id = rec.get(1);
            Double rating = Double.parseDouble(rec.get(2));
            String time = rec.get(3); // we don't use this

            // see if rater_id is already in raterlist
            int index = 0;
            boolean notFound = true;
            while (index < raterlist.size() && notFound) {
                Rater r = raterlist.get(index);
            
                if (r.getID().equals(rater_id.trim())) {
                    // if rater_id is already in raterlist,
                    notFound = false;

                    // check if the item is already rated.
                    if (r.hasRating(movie_id) == false) {
                        // we should add the rating to the entry
                        r.addRating(movie_id, rating);

                        // break rest of loop
                    }
                    raterlist.set(index,r);
                    break;
                }
                
                // look at the nest Rater in the raterlist
                index++;
            }
            //if not found in loop, then rater_id is not yet in raterlist
            if (notFound) {
                //Rater r = new PlainRater(rater_id);
                Rater r = new EfficientRater(rater_id);
                r.addRating(movie_id, rating);
                raterlist.add(r);
            }
        }
        
        /*
         * return an ArrayList of type Rater
         * with all the rater data from the file.
         */
        return raterlist;
    }
    
    /**
     * Find number of ratings by a rater
     */
    public int numRatingsbyRater(ArrayList<Rater> raterlist, String id) {
        // check the whole list if necessary
        for (Rater r : raterlist) {
            // for each Rater, check the ID
            if (r.getID().equals(id)) {
                // if it's a match, return the number of rated items
                return r.getItemsRated().size();
            }
        }
        // if we didn't find that user, return 0;
        return 0;
    }

    /**
     * Max num ratings
     */
    public HashMap<Integer, ArrayList<String>> maxNumRatings(ArrayList<Rater> raterlist) {
        /* find the maximum number of ratings by any rater. 
         * Determine how many raters have this maximum number of ratings 
         * and who those raters are. */

        int maxcount = 0;
        ArrayList<String> ratersmax = new ArrayList<String>();

        // iterate through entire raterlist
        for (Rater r : raterlist) {
            //get Ratings
            int numRated = r.getItemsRated().size();
            if (numRated > maxcount) {
                // found a new max
                maxcount = numRated;
                ratersmax.clear();
                ratersmax.add(r.getID());
            }
            else if (numRated == maxcount){
                // found a tie with old max
                ratersmax.add(r.getID());
            }
        }

        HashMap<Integer, ArrayList<String>> maxMap = new HashMap<Integer, ArrayList<String>>();
        maxMap.put(maxcount, ratersmax);
        return maxMap;
    }
    
    /**
     * Find all the raters of a given movie
     */
    public ArrayList<String> getRatersByMovie(ArrayList<Rater> raterlist, String movie_id) {
        ArrayList<String> raters = new ArrayList<String>();
        
        // iterate through entire raterlist
        for (Rater r : raterlist) {
            //get Ratings
            ArrayList<String> items = r.getItemsRated();
            if (items.contains(movie_id)) {
                raters.add(r.getID());
            }
        }
        
        return raters;
    }
    
    /**
     * Unique movies by all raters
     */
    public ArrayList<String> uniqueMovies(ArrayList<Rater> raterlist) {
        ArrayList<String> movies = new ArrayList<String>();
        
        // iterate through entire raterlist
        for (Rater r : raterlist) {
            //get Movies
            ArrayList<String> items = r.getItemsRated();
            // add any that aren't in the movies list yet
            for (String item : items) {
                if (!movies.contains(item)) {
                    movies.add(item);
                }
            }
        }
        return movies;
    }
    
    /**
     * testLoadRaters
     */
    public void testLoadRaters() {
        //ArrayList<Rater> raterlist = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> raterlist = loadRaters("data/ratings.csv");
        System.out.println("Total number of raters: " + raterlist.size());
        
        // Then for each rater, print rater’s ID and number of ratings on one line, 
        // followed by each rating (both movie ID and rating given) on a separate line.
        // If you run your program on the file ratings_short.csv there are five raters.
        /*for (Rater r : raterlist) {
            // process each item in turn 
            System.out.println("ID:" + r.getID() + " #ratings: " + r.getItemsRated().size());

            ArrayList<String> ratings = r.getItemsRated();
            for (String item : ratings) {
                System.out.println(item + " : " + r.getRating(item));
            }
        }*/
        // After it looks like it works, you may want to comment out 
        // the printing of each rater and their ratings.

        // If you run your program on the file ratings.csv, you should get 1048 raters.

        // find the number of ratings for a particular rater.
        // For example, if you run this code on the rater whose rater_id is 2 
        // for the file ratings_short.csv, you will see they have three ratings. */
        //int num = numRatingsbyRater(raterlist, "2");
        int num = numRatingsbyRater(raterlist, "193");
        System.out.println("Number of ratings by the given rater: " + num);
        
        // find the maximum number of ratings by any rater. 
        // Determine how many raters have this maximum number of ratings 
        // and who those raters are. 
        // If you run this code on the file ratings_short.csv, 
        // you will see rater 2 has three ratings, 
        // the maximum number of ratings of all the raters, 
        // and that there is only one rater with three ratings. */
        HashMap<Integer, ArrayList<String>> maxMap = maxNumRatings(raterlist);
        //System.out.println(maxMap);
        for (Integer number : maxMap.keySet()) {
            System.out.println("Max Number of ratings by any given rater: "+ number);
            ArrayList<String> raters = maxMap.get(number);
            //System.out.println("Number of raters who have rated that many movies: " + raters.size());
            System.out.println("IDs of raters that have that many ratings: " + raters);
        }
        
        // find the number of ratings a particular movie has.
        // If you run this code on the file ratings_short.csv for the movie “1798709”,
        // you will see it was rated by four raters.
        ArrayList<String> raters = getRatersByMovie(raterlist, "1798709");
        System.out.println("Given movie was rated by " + raters.size() + " raters");
        
        // Determine how many different movies have been rated by all these raters.
        // If you run this code on the file ratings_short.csv, you will see there were
        // four movies rated.
        ArrayList<String> movies = uniqueMovies(raterlist);
        System.out.println("Movies rated: " + movies.size());
    }
}
