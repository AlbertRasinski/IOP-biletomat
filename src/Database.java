import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String url = "jdbc:mysql://localhost:3306/iop_db?autoReconnect=true&useSSL=false";
    private final String user = "root";
    private final String password = "haslo";
    private Connection connection;
    private Statement statement;

    public Database(){
        try{
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet executeQuerry(String sqlQuerry){
        try {
            return statement.executeQuery(sqlQuerry);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateQuerry(String sqlQuerry){
        try {
            statement.executeUpdate(sqlQuerry);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
