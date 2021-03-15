import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hall {
    private int rows;
    private int places;
    private int hallNumber;
    private ArrayList<Place> placeArray;

    public Hall(){
        placeArray = new ArrayList<Place>();
    }

    public void setRowsPlaces(int value1,int value2){
        rows = value1;
        places = value2;
        placeArray.removeAll(placeArray);
        for (int i = 0; i < (rows * places - rows / 2); ++i){
            placeArray.add(new Place());
        }
    }

    public void setHallNumber(int value){
        hallNumber = value;
    }


    public int getRows(){
        return rows;
    }

    public int getPlaces(){
        return places;
    }

    public int getHallNumber(){
        return hallNumber;
    }

    public Place getPlace(int i){
        return placeArray.get(i);
    }

    public ArrayList<String> pickedPlaces(){
        ArrayList<String> ar = new ArrayList<>();
        for (int i = 0; i < placeArray.size(); ++i){
            if (placeArray.get(i).getStatus() == Place.Status.picked){
                ar.add(convertToPlace(i));
            }
        }
        return ar;
    }

    public void setTakenPlaces(Database db, String city, String network, String date, String movieName){
        ResultSet rs = db.executeQuerry("SELECT bilet.rzad,bilet.miejsce FROM bilet INNER JOIN seans ON seans.idseans=bilet.idseans INNER JOIN sala ON seans.idsala=sala.idsali INNER JOIN kino ON sala.idkina=kino.idkino INNER JOIN film ON film.idfilm=seans.idfilm INNER JOIN sieckin ON sieckin.idSiecKin=kino.idSieciKina WHERE film.nazwa='" + movieName + "' AND kino.Miasto=\"" + city + "\" AND sieckin.Nazwa='" + network + "' AND seans.start='" + date + "';");
        try {
            while(rs.next()){
                int i = ((int) rs.getString("rzad").charAt(0)) - 64;
                int j = rows - i + 1;
                int sum = 0;
                for (int k = 1; k < j; ++k){
                    if (k % 2 != 0){
                        sum += places;
                    }else{
                        sum += (places - 1);
                    }
                }
                sum += rs.getInt("miejsce");
                --sum;
                placeArray.get(sum).setTaken();
            };
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void reset(){
        for (int i = 0; i < placeArray.size(); ++i){
            if (placeArray.get(i).getStatus() == Place.Status.picked){
                placeArray.get(i).pickPlace();
            }
        }
    }

    private String convertToPlace(int i){
        int j = (i / (2 * places - 1));
        int maxSeats = rows * places - rows / 2;
        int row_ = rows - ((i + j) / places);
        char row = (char) (64 + row_);

        int x = i - j * (places - 1) - ((i - j * (places - 1)) / places) * places + 1;
        String result = Character.toString(row) + Integer.toString(x);
        return result;
    }
}
