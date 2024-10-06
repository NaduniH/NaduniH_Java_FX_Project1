package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;

    Connection connection;

    private DBConnection(){
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadefx", "root", "root");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static DBConnection getInstance() {
        return instance==null ? instance=new DBConnection():instance;

    }

    public Connection getConnection(){
        return connection;
    }


}
