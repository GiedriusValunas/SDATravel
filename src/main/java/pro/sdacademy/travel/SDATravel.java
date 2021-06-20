package pro.sdacademy.travel;

import pro.sdacademy.travel.entity.Client;
import pro.sdacademy.travel.repository.ClientRepository;

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
    private ClientRepository clientRepository;

    public SDATravel(String dbUrl, String username, String password) {
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            clientRepository = new ClientRepository(connection);
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    public void run() {
//        System.out.println(clientRepository.find(1).orElse(null));
//        testCreate();
        Client c = new Client();
        c.setId(2);
        clientRepository.delete(c);
        clientRepository.findAll().forEach(System.out::println);
    }

    public void testCreate() {
        Client client = new Client();
        client.setName("John");
        client.setSurname("Smith");
        client.setBirthdate(LocalDate.now().minus(18, ChronoUnit.YEARS));
        clientRepository.create(client);
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
