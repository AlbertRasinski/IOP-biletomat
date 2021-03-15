public class Movie {
    private String movieName;
    private byte[] poster;

    public void setMovieName(String name){
        movieName = name;
    }

    public void setPoster(byte[] posterByte){
        poster = posterByte;
    }

    public String getMovieName(){
        return movieName;
    }

    public byte[] getPoster(){
        return poster;
    }
}
