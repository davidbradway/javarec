/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    /**
     * This method returns a list of movie IDs that will be used to look up 
     * the movies in the MovieDatabase and present them to users to rate. 
     *  
     * The movies returned in the list will be displayed on a web page, so
     * the number you choose may affect how long the page takes to load and
     * how willing users are to rate the movies.  For example, 10-20 should
     * be fine, 50 or more would be too many.
     * 
     * There are no restrictions on the method you use to generate this list
     * of movies: the most recent movies, movies from a specific genre, 
     * randomly chosen movies, or simply your favorite movies.
     * 
     * The ratings for these movies will make the profile for a new Rater 
     * that will be used to compare to for finding recommendations.
     */
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> ret = new ArrayList<String>();
        for (int k=0; k<20; k++) {
            Random rand = new Random();
            int  n = rand.nextInt(movies.size());
            ret.add(movies.get(n));
        }
        return ret;
    }

    /**
     * This method returns nothing, but prints out an HTML table of the 
     * movies recommended for the given rater.
     * 
     * The HTML printed will be displayed on a web page, so the number you
     * choose to display may affect how long the page takes to load.  For 
     * example, you may want to limit the number printed to only the top 
     * 20-50 movies recommended or to movies not rater by the given rater.
     * 
     * You may also include CSS styling for your table using the &lt;style&gt;
     * tag before you print the table.  There are no restrictions on which 
     * movies you print, what order you print them in, or what information
     * you include about each movie. 
     * 
     * @param webRaterID the ID of a new Rater that has been already added to 
     *        the RaterDatabase with ratings for the movies returned by the 
     *        method getItemsToRate
     */
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> simRatings = fr.getSimilarRatings(webRaterID, 20, 5);

        int maxDisplay = 15;
        if (simRatings.size() < maxDisplay) maxDisplay = simRatings.size();
        if (maxDisplay == 0) {
            // print an error message instead of a table
            System.out.println("<p>No results returned</p>");
        }
        else {
            System.out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/"+
                               "css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-9g"+
                               "VQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4\""+
                               "crossorigin=\"anonymous\">");
            System.out.println("<table class=\"table table-sm table-hover table-striped\"><thead"+
                               "><tr><th scope=\"col\">#</th>"+
                               "<th scope=\"col\">Poster</th>"+
                               "<th scope=\"col\">Title</th>"+
                               "<th scope=\"col\">Genres</th>"+
                               "<th scope=\"col\">Minutes</th>"+
                               "<th scope=\"col\">Year</th></tr></thead><tbody>");
            for (int k=0; k<maxDisplay; k++) {
                int row = k + 1;
                String url = MovieDatabase.getPoster(simRatings.get(k).getItem());
                if (url == null) url="&nbsp;";
                String title = MovieDatabase.getTitle(simRatings.get(k).getItem());
                if (title == null) title="&nbsp;";
                String genres = MovieDatabase.getGenres(simRatings.get(k).getItem());
                if (genres == null) genres="&nbsp;";
                int minutes = MovieDatabase.getMinutes(simRatings.get(k).getItem());
                int year = MovieDatabase.getYear(simRatings.get(k).getItem());
                System.out.println("<tr><th scope=\"row\">"+row+"</th>"+
                                    "<td><img src=\""+url+"\" height=\"50\"></td>"+
                                    "<td>"+title+"</td>"+
                                    "<td>"+genres+"</td>"+
                                    "<td>"+minutes+"</td>"+
                                    "<td>"+year+"</td></tr>");
            }
            System.out.println("</tbody></table>");
        }
    }

    public void testGetItemsToRate() {
        ArrayList<String> movies = getItemsToRate();
        for (String movieID : movies) {
            System.out.println(movieID);
        }
    }
    
    public void testPrintRecommendationsFor() {
        printRecommendationsFor("65");
    }
}
