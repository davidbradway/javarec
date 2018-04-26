/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {

    public FourthRatings(String filename) {
        RaterDatabase.initialize(filename);
    }
    
    private double dotProduct(Rater me, Rater r){
        double weight = 0.0;
        ArrayList<String> movies = me.getItemsRated();
        for (String movieID: movies) {
            if (r.hasRating(movieID)) {
                weight += (me.getRating(movieID)-5.0)*(r.getRating(movieID)-5.0);
            }
        }
        return weight;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()){
            String otherID = r.getID();
            if (!id.equals(otherID)) {
                // do dot product between these two raters
                double similarity = dotProduct(me, r);
                if (similarity > 0) {
                    Rating rating = new Rating(otherID, similarity);
                    list.add(rating);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        // list contains raterIDs & similarity metrics
        ArrayList<Rating> list = getSimilarities(id);
        // new list for results
        ArrayList<Rating> rated = new ArrayList<Rating>();
        // prevent out of bounds error
        if (numSimilarRaters > list.size()) numSimilarRaters = list.size();

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            // don't recommend movies the rater has already seen
            //if (!RaterDatabase.getRater(id).hasRating(movieID)) {
                double weightSum = 0.0;
                int weightCount = 0;
                for (int k=0; k<numSimilarRaters; k++) {
                    // This 'Rating' below is really a list of Rater similarities
                    Rating r = list.get(k);
    
                    //consider only Raters with positive similarities
                    double similarity = r.getValue();
                    if (similarity > 0){
                        // look up the Rater in the RaterDatabase by ID (key in r)
                        Rater rater = RaterDatabase.getRater(r.getItem());
                        // did this rater rate this movieID?
                        if (rater.hasRating(movieID)){
                            double rating = rater.getRating(movieID);
                            weightSum += similarity * rating;
                            weightCount++;
                        }
                    }
                }
                if (weightCount >= minimalRaters) {
                    // Put these in an ArrayList rated, and Sort them.
                    double weightedAverage = weightSum/weightCount;
                    //System.out.println(movieID + " Weighted average " + weightedAverage);
                    Rating rating = new Rating(movieID, weightedAverage);
                    rated.add(rating);
                }
            //}
        }
        Collections.sort(rated, Collections.reverseOrder());
        return rated;
    }

    private double getAverageByID(String id, int minimalRaters) {
        // return double representing the average movie rating for this ID if there are at
        // least minimalRaters ratings.
        int raters = 0;
        double total = 0.0;
        
        for (Rater r : RaterDatabase.getRaters()) {
            if (r.hasRating(id)) {
                total += r.getRating(id);
                raters++;
            }
        }
        if (raters >= minimalRaters) {
            return total/raters;
        }
        // If there are not minimalRaters ratings, then it returns 0.0.
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        // find the average rating for every movie rated by at least minimalRaters raters.
        // The method getAverageRatings should return an ArrayList of all the Rating objects
        // for movies that have at least the minimal number of raters supplying a rating.
        return getAverageRatingsByFilter(minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        
        // get movies from the MovieDatabase, store them in an ArrayList of movie IDs. 
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String m : movies) {
            double avg = getAverageByID(m, minimalRaters);
            
            // can't tell difference between a 0.0 rating and movie with no or few ratings
            if (avg > 0.0) {
                avgRatings.add(new Rating(m, avg));
            }
        }
        return avgRatings;
    }
}
