import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private boolean loggedIn;
    private int loginOption;
    private Database database;
    private int id;

    public User(Database db){
        database = db;
        loginOption = 0;
    }

    public boolean login(String login, String password){
        ResultSet rs = database.executeQuerry("SELECT * FROM uzytkownik WHERE login='" + login +"' AND haslo='" + password + "';");
        try {
            if (rs.next()){
                if (rs.getString("login").equals(login) && rs.getString("haslo").equals(password)){
                    loggedIn = true;
                    id = rs.getInt("iduzyt");
                    return true;
                }else{
                    loggedIn = false;
                    return false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    };

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public int getId(){
        return id;
    }

    public void logOut(){
        System.out.println(id);
        loggedIn = false;
        loginOption = 0;
    }

    public void setLoginOption(int x){
        loginOption = x;
        System.out.println(loginOption);
    }

    public int getLoginOption(){
        return loginOption;
    }

    public ArrayList<String> getTickets(){
        ArrayList<String> al = new ArrayList<>();
        ResultSet rs = database.executeQuerry("SELECT film.nazwa,kino.Miasto,sieckin.Nazwa AS NazwaSK,kino.adres,sala.numerSali,seans.start, bilet.rzad, bilet.miejsce FROM seans INNER JOIN bilet ON bilet.idseans=seans.idseans INNER JOIN sala ON sala.idsali=seans.idsala INNER JOIN kino ON kino.idkino=sala.idkina INNER JOIN sieckin ON sieckin.idSiecKin=kino.idSieciKina INNER JOIN film ON film.idfilm=seans.idfilm WHERE bilet.iduzyt=" + id +";");
        try {
            while(rs.next()){
                al.add(rs.getString("nazwa") + " " + rs.getString("Miasto") + " " + rs.getString("NazwaSK") + " " + rs.getString("adres") + " " + rs.getInt("numerSali") + " " + rs.getString("start") + " " + rs.getString("rzad") + rs.getString("miejsce"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (String s : al){
            System.out.println(s);
        }
        return al;
    }

}
