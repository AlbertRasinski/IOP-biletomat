import java.util.Date;

public class Screening {
    private int id;
    private Movie movie;
    private String start;
    private int hallNumber;

    public void setId(int idValue){
        id = idValue;
    }

    public void setStart(String start){
        this.start = start;
    }

    public void setHall(int hall){
        hallNumber = hall;
    }

    public void setMovie(String name, byte[] posterByte){
        movie = new Movie();
        movie.setMovieName(name);
        movie.setPoster(posterByte);
    }

    public int getHall(){
        return hallNumber;
    }

    public String getStart(){
        return start;
    }

    public Movie getMovie(){
        return movie;
    }
}
