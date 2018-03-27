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
    private ArrayList<Movie> loadMovies(String filename) {
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
    
    private ArrayList<Movie> filterByGenre(ArrayList<Movie> movielist, String genre) {

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

    private ArrayList<Movie> filterByLengthLonger(ArrayList<Movie> movielist, int minlength) {

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
    private HashMap<Integer, ArrayList<String>> countDirectors(ArrayList<Movie> movielist){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        int maxcount = 0;
        ArrayList<String> directorsmax = new ArrayList<String>();
        
        // iterate through entire movielist
        for (Movie m : movielist) {
            // get Directors
            String d = m.getDirector();
            int start = 0;
            int end;
            boolean notAtEnd = true;

            // Want to split directors string on commas.
            // Find the first comma (or -1 if none)
            end = d.indexOf(",", start);
            
            while(end != -1 || notAtEnd) {
                // if there are no more commas, still add the last director
                // and make the flag false so we don't search again
                if (end == -1){
                    end = d.length();
                    notAtEnd = false;
                }
                
                String director = d.substring(start, end).trim();

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
                
                // start searching after the last comma we found
                start = end + 1;
                end = d.indexOf(",", start);
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
        
        ArrayList<Movie> movielist = loadMovies("data/ratedmovies_short.csv"); //5
        //ArrayList<Movie> movielist = loadMovies("data/ratedmoviesfull.csv"); //3143

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
    private ArrayList<Rater> loadRaters(String filename) {
        /*
         * process every record from the CSV file whose name is filename,
         * a file of raters and their ratings,
         * and return an ArrayList of type Rater
         * with all the rater data from the file.
         */
        
        return null;
    }
    
    /**
     * testLoadRaters
     */
    private void testLoadRaters() {
        ArrayList<Rater> raterlist = loadRaters("ratings_short.csv");
        /*
         * Print the total number of raters. Then for each rater, print the rater’s ID and the number of ratings they did on one line, followed by each rating (both the movie ID and the rating given) on a separate line. If you run your program on the file ratings_short.csv you will see there are five raters. After it looks like it works, you may want to comment out the printing of each rater and their ratings. If you run your program on the file ratings.csv, you should get 1048 raters.
         * Add code to find the number of ratings for a particular rater you specify in your code. For example, if you run this code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they have three ratings.
         * Add code to find the maximum number of ratings by any rater. Determine how many raters have this maximum number of ratings and who those raters are. If you run this code on the file ratings_short.csv, you will see rater 2 has three ratings, the maximum number of ratings of all the raters, and that there is only one rater with three ratings.
         * Add code to find the number of ratings a particular movie has. If you run this code on the file ratings_short.csv for the movie “1798709”, you will see it was rated by four raters.
         * Add code to determine how many different movies have been rated by all these raters. If you run this code on the file ratings_short.csv, you will see there were four movies rated.
         */
    }
}
