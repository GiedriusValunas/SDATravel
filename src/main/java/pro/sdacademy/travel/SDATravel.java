package pro.sdacademy.travel;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pro.sdacademy.travel.entity.Client;
import pro.sdacademy.travel.entity.Trip;
import pro.sdacademy.travel.entity.TripOrder;
import pro.sdacademy.travel.repository.ClientRepository;
import pro.sdacademy.travel.repository.TripOrderRepository;
import pro.sdacademy.travel.repository.TripRepository;
import pro.sdacademy.travel.test.ClientTestCase;
import pro.sdacademy.travel.test.TestRunner;
import pro.sdacademy.travel.test.TripOrdersTestCase;
import pro.sdacademy.travel.test.TripTestCase;

import javax.persistence.EntityManager;

public class SDATravel implements AutoCloseable {

    private EntityManager entityManager;
    private TripRepository tripRepository;
    private ClientRepository clientRepository;
    private TripOrderRepository tripOrderRepository;

    public SDATravel() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Trip.class)
                .addAnnotatedClass(TripOrder.class)
                .buildSessionFactory();

        entityManager = sessionFactory.createEntityManager();
        tripRepository = new TripRepository(entityManager);
        clientRepository = new ClientRepository(entityManager);
        tripOrderRepository = new TripOrderRepository(entityManager);
    }

    public void run() {
        ClientTestCase clientTestCase = new ClientTestCase(clientRepository);
//        TestRunner.runTests(clientTestCase);
        TripTestCase tripTestCase = new TripTestCase(tripRepository);
//        TestRunner.runTests(tripTestCase);
        TestRunner.runTests(new TripOrdersTestCase(tripOrderRepository, clientTestCase, tripTestCase));
    }

    @Override
    public void close() {
        entityManager.close();
    }

    public static void main(String[] args) {
        try (SDATravel travelApp = new SDATravel()) {
            travelApp.run();
        }
    }
}
