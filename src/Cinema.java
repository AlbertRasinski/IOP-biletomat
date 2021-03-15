import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Cinema {
    private int id;
    private String city;
    private String network;
    private String address;

    private final String CURRENT_CITY = "Warszawa";
    private final String CURRENT_NETWORK = "Regal";
    private final String CURRENT_ADDRESS = "szpitalna 15";
    private final int CURRENT_ID = 13;

    public Database database;
    private ArrayList<Screening> screeningsInCinema;
    private ArrayList<Hall> cinemaHalls;

    public Cinema(Database db){
        database = db;
        id = CURRENT_ID;
        city = CURRENT_CITY;
        network = CURRENT_NETWORK;
        address = CURRENT_ADDRESS;
        screeningsInCinema = new ArrayList<Screening>();
        cinemaHalls = new ArrayList<Hall>();
    }

    public void setCinemaValues(String city, String network, String address){
        this.city = city;
        this.network = network;
        this.address = address;
        this.id = 0;
        if (!(city.equals("") && network.equals("") && address.equals(""))){
            ResultSet rs = database.executeQuerry("SELECT idKino FROM kino WHERE Miasto=\"" + this.city +"\" AND adres=\"" + this.address + "\";");
            try {
                rs.next();
                this.id = rs.getInt("idKino");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    
    public void setCinemaValuesToInitial(){
        city = CURRENT_CITY;
        network = CURRENT_NETWORK;
        address = CURRENT_ADDRESS;
        id = CURRENT_ID;
    }

    public ResultSet findCity(){
        return database.executeQuerry("SELECT DISTINCT kino.Miasto FROM kino ORDER BY kino.Miasto ASC;");
    }

    public ResultSet findNetwork(String city){
        if (city.equals("")){
            return database.executeQuerry("SELECT sieckin.Nazwa FROM sieckin ORDER BY sieckin.Nazwa ASC;");
        }else{
            return database.executeQuerry("SELECT DISTINCT sieckin.Nazwa FROM sieckin INNER JOIN kino ON kino.idSieciKina=sieckin.idSiecKin WHERE kino.Miasto='" + city + "' ORDER BY sieckin.Nazwa ASC;");
        }
    }

    public ResultSet findAddress(String city, String network){
        ResultSet resultSet;
        if (network.equals("")){
            if (city.equals("")){
                resultSet = database.executeQuerry("SELECT kino.Miasto, sieckin.Nazwa, kino.Adres FROM kino INNER JOIN sieckin ON sieckin.idSiecKin=kino.idSieciKina ORDER BY kino.Miasto ASC, sieckin.Nazwa ASC, kino.Adres ASC;");
            }else{
                resultSet = database.executeQuerry("SELECT kino.Miasto, sieckin.Nazwa, kino.Adres FROM kino INNER JOIN sieckin ON sieckin.idSiecKin=kino.idSieciKina WHERE kino.Miasto='" + city + "'ORDER BY kino.Miasto ASC, sieckin.Nazwa ASC, kino.Adres ASC;");
            }
        }else{
            if (city.equals("")){
                resultSet = database.executeQuerry("SELECT kino.Miasto, sieckin.Nazwa, kino.Adres FROM kino INNER JOIN sieckin ON sieckin.idSiecKin=kino.idSieciKina WHERE sieckin.Nazwa='" + network + "' ORDER BY kino.Miasto ASC, sieckin.Nazwa ASC, kino.Adres ASC;");
            }else{
                resultSet = database.executeQuerry("SELECT kino.Miasto, sieckin.Nazwa, kino.Adres FROM kino INNER JOIN sieckin ON sieckin.idSiecKin=kino.idSieciKina WHERE kino.Miasto='" + city + "' AND sieckin.Nazwa='" + network + "' ORDER BY kino.Miasto ASC, sieckin.Nazwa ASC, kino.Adres ASC;");
            }
        }
        return resultSet;
    }

    public String getCinema(){
        return city + " " + network + " " + address;
    }

    public void setCinemaHalls(){
        cinemaHalls.removeAll(cinemaHalls);
        ResultSet resultSet = database.executeQuerry("SELECT sala.rzedy,sala.miejsca,sala.numerSali FROM sala WHERE idkina=" + Integer.toString(id) + ";");
        try {
            while(resultSet.next()){
                Hall tmpHall = new Hall();
                tmpHall.setRowsPlaces(resultSet.getInt("rzedy"),resultSet.getInt("miejsca"));
                tmpHall.setHallNumber(resultSet.getInt("numerSali"));
                cinemaHalls.add(tmpHall);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setScreenings(){
        screeningsInCinema.removeAll(screeningsInCinema);
        ResultSet resultSet = database.executeQuerry("SELECT seans.idseans,film.nazwa,sala.numerSali,seans.start,film.plakat FROM seans INNER JOIN film ON film.idfilm=seans.idfilm INNER JOIN sala ON seans.idsala=sala.idsali INNER JOIN kino ON sala.idkina=kino.idkino WHERE kino.idkino=" + Integer.toString(id) + ";");
        try {
            while(resultSet.next()){
                Screening tmpScreening = new Screening();
                tmpScreening.setId(resultSet.getInt("idseans"));
                tmpScreening.setStart(resultSet.getString("start"));
                tmpScreening.setHall(resultSet.getInt("numerSali"));
                tmpScreening.setMovie(resultSet.getString("nazwa"),resultSet.getBytes("plakat"));
                screeningsInCinema.add(tmpScreening);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Movie> getUniqueMovies(){
        ResultSet resultSet = database.executeQuerry("SELECT DISTINCT(film.nazwa),film.plakat FROM seans INNER JOIN film ON film.idfilm=seans.idfilm INNER JOIN sala ON seans.idsala=sala.idsali INNER JOIN kino ON sala.idkina=kino.idkino WHERE kino.idkino=" + Integer.toString(id) + ";");
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {
            while(resultSet.next()){
                Movie movie = new Movie();
                movie.setMovieName(resultSet.getString("nazwa"));
                movie.setPoster(resultSet.getBytes("plakat"));
                movies.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;
    }

    public int getIdScreening(String movieName, String date){
        ResultSet rs = database.executeQuerry("SELECT seans.idseans FROM bilet INNER JOIN seans ON seans.idseans=bilet.idseans INNER JOIN sala ON seans.idsala=sala.idsali INNER JOIN kino ON sala.idkina=kino.idkino INNER JOIN film ON film.idfilm=seans.idfilm INNER JOIN sieckin ON sieckin.idSiecKin=kino.idSieciKina WHERE film.nazwa='" + movieName + "' AND kino.Miasto=\"" + city + "\" AND sieckin.Nazwa='" + network + "' AND seans.start='" + date + "';");
        try {
            rs.next();
            return rs.getInt("idseans");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    public byte[] getChosenMoviePoster(String name){
        ArrayList<Movie> al = getUniqueMovies();
        for (Movie m : al){
            if (name.equals(m.getMovieName())){
                return m.getPoster();
            }
        }
        return null;
    }

    public ArrayList<String> getTimeByDay(Date date, String movieName){
        ArrayList<String> times = new ArrayList<String>();

        String day = getStringDate(date);
        times.add(day);

        String[] tmpArr = new String[2];

        for (Screening scr : screeningsInCinema){
            if (scr.getMovie().getMovieName().equals(movieName)){
                tmpArr = scr.getStart().split(" ");
                if (day.equals(tmpArr[0])){
                    times.add(tmpArr[1].substring(0,5));
                }
            }
        }
        return times;
    }

    private String getStringDate(java.util.Date time){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time);

        int year = calendar1.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH);
        ++month;
        int day = calendar1.get(Calendar.DAY_OF_MONTH);
        String resultDate = year + "-";
        if (month < 10){
            resultDate += "0";
        }
        resultDate += Integer.toString(month);
        resultDate += "-";
        if (day < 10){
            resultDate += "0";
        }
        resultDate += Integer.toString(day);
        return resultDate;
    }

    public int getHallNumber(String date){
        for (Screening scr : screeningsInCinema){
            if (scr.getStart().equals(date)){
                return scr.getHall();
            }
        }
        return 0;
    }

    public Hall getHall(int number){
        for (Hall h : cinemaHalls){
            if (h.getHallNumber() == number){
                return h;
            }
        }
        return null;
    }
}
