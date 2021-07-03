package pro.sdacademy.travel;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pro.sdacademy.travel.entity.Client;
import pro.sdacademy.travel.entity.Trip;
import pro.sdacademy.travel.repository.ClientRepository;
import pro.sdacademy.travel.repository.TripOrderRepository;
import pro.sdacademy.travel.repository.TripRepository;
import pro.sdacademy.travel.test.ClientTestCase;
import pro.sdacademy.travel.test.TestRunner;
import pro.sdacademy.travel.test.TripOrdersTestCase;
import pro.sdacademy.travel.test.TripTestCase;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SDATravel implements AutoCloseable {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sdatravel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Dd%UB#s7x5yxDrhD";

    private Connection connection;
    private TripRepository tripRepository;
    private ClientRepository clientRepository;
    private TripOrderRepository tripOrderRepository;

    public SDATravel(String dbUrl, String username, String password) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Trip.class)
                .buildSessionFactory();

        EntityManager entityManager = sessionFactory.createEntityManager();

        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            tripRepository = new TripRepository(entityManager);
            clientRepository = new ClientRepository(entityManager);
            tripOrderRepository = new TripOrderRepository(connection, tripRepository, clientRepository);

        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    public void run() {
        ClientTestCase clientTestCase = new ClientTestCase(clientRepository);
//        TestRunner.runTests(clientTestCase);
        TripTestCase tripTestCase = new TripTestCase(tripRepository);
//        TestRunner.runTests(tripTestCase);
        TestRunner.runTests(new TripOrdersTestCase(tripOrderRepository, clientTestCase, tripTestCase));
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
