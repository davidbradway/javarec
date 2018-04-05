/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String myDirectors;
    
    public DirectorsFilter(String directors) {
        /**
          * (Example: "Charles Chaplin,Michael Mann,Spike Jonze"
          */
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        /**
         * return true if a movie has at least one of these directors as one of its directors.
         * Note that each movie may have several directors.
         */
        
        // Want to split directors string on commas.
        // Find the first comma (or -1 if none)
        int start = 0;
        int end = myDirectors.indexOf(",", start);
        boolean notAtEnd = true;

        while(end != -1 || notAtEnd) {
            // if there are no more commas, still add the last director
            // and make the flag false so we don't search again
            if (end == -1){
                end = myDirectors.length();
                notAtEnd = false;
            }
            
            String director = myDirectors.substring(start, end).trim();
            if (MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
            
            // start searching after the last comma we found
            start = end + 1;
            end = myDirectors.indexOf(",", start);
        }
        return false;
    }
}
