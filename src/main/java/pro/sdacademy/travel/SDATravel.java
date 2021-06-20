package pro.sdacademy.travel;

import pro.sdacademy.travel.entity.Client;
import pro.sdacademy.travel.repository.ClientRepository;
import pro.sdacademy.travel.repository.TripRepository;
import pro.sdacademy.travel.test.ClientTestCase;
import pro.sdacademy.travel.test.TestRunner;
import pro.sdacademy.travel.test.TripTestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SDATravel implements AutoCloseable {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sdatravel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Dd%UB#s7x5yxDrhD";

    private Connection connection;
    private TripRepository tripRepository;
    private ClientRepository clientRepository;

    public SDATravel(String dbUrl, String username, String password) {
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            tripRepository = new TripRepository(connection);
            clientRepository = new ClientRepository(connection);

        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    public void run() {
//        TestRunner.runTests(new ClientTestCase(clientRepository));
        TestRunner.runTests(new TripTestCase(tripRepository));
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        try (SDATravel travelApp = new SDATravel(DB_URL, USERNAME, PASSWORD)) {
            travelApp.run();
        }
    }
}
