import java.util.ArrayList;

public class Ticket {
    private String city;
    private String network;
    private String address;
    private int idScreening;
    private String movieName;
    private String day;
    private String time;
    private int hallNumber;
    private ArrayList<String> places;
    private Database database;

    public Ticket(Database db){
        places = new ArrayList<>();
        database = db;
    }

    public void setCity(String value){
        city = value;
    }

    public void setNetwork(String value){
        network = value;
    }

    public void setAddress(String value){
        address = value;
    }

    public void setMovieName(String value){
        movieName = value;
    }

    public void setDay(String day){
        this.day = day;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setHallNumber(int number){
        hallNumber = number;
    }

    public void setPlaces(ArrayList<String> places1){
        places = places1;
    }

    public void setIdScreening(int id){
        idScreening = id;
    }

    public String getCity(){
        return city;
    }

    public String getNetwork(){
        return network;
    }

    public String getAddress(){
        return address;
    }

    public String getmovieName(){
        return movieName;
    }

    public String getDay(){
        return day;
    }

    public String getTime(){
        return time;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public ArrayList<String> getPlaces(){
        return places;
    }

    public void orderTicket(int uzyt){
        for (String s : places){
            database.updateQuerry("INSERT INTO bilet VALUES(NULL," + idScreening + ", '" + s.substring(0,1) + "'," + s.substring(1,s.length()) + ",1," + uzyt + ");");
        }
    }
}
