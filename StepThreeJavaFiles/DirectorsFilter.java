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
        
        // Split directors String on commas
        String directorarray[] = myDirectors.split(",");
        for (String director: directorarray){
            if (MovieDatabase.getDirector(id).contains(director)) {
                return true;
            }
        }
        return false;
    }
}
