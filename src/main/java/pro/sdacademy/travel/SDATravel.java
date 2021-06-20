package pro.sdacademy.travel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SDATravel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sdatravel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "***";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            System.out.println(conn);
        }
    }
}
